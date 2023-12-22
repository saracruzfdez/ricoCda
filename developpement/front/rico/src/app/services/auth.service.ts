import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtTokenService } from './jwt-token.service';
import { User } from '../interfaces/user.interface';
import { environment } from 'src/environments/environment';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private router: Router,
    private http: HttpClient,
    private jwtTokenService: JwtTokenService
  ) { }

  // Méthode pour vérifier si un utilisateur est authentifié + token n'a pas expiré
  public isAuth = (): boolean => {

    // Récupérez le token depuis le stockage
    const token = this.getToken();

    if (token) {
      // Configurez le service de gestion des jetons avec le token récupéré
      this.jwtTokenService.setToken(token);

      // Retournez true si le token n'a pas expiré, indiquant que l'utilisateur est authentifié
      return !this.jwtTokenService.isTokenExpired();
    }

    // Retournez false si aucun token n'a été trouvé, indiquant que l'utilisateur n'est pas authentifié
    return false;
  }

  public getToken = (): string | null => {
    return sessionStorage.getItem('token');
  }

  private setToken = (token: string) => {
    sessionStorage.setItem("token", token);
  }

  public isAdmin = (): boolean => {
    // Récupère l'objet utilisateur à partir du session storage
    const user = this.getUser();
    // Vérifie si un utilisateur a été récupéré
    if (user) {
      // Utilise Array.some() pour vérifier si au moins un rôle a l'autorité "ADMIN"
      //return user.roles.some(role => role.authority === "ADMIN");
    }
    // Si aucun utilisateur n'a été récupéré ou si l'utilisateur n'a pas le rôle "ADMIN", retourne false
    return false;
  }

  // Après une inscription ou authentification réussi
  public doLogged = (data: any): void => {
    this.setToken(data.token)
    this.setUser(data.user)
    this.router.navigate([""]);
  }

  public getUser = (): User | null => {
    // Récupérez la chaîne JSON du sessionStorage
    const userJSON = sessionStorage.getItem('user');
    if (userJSON) {
      // Désérialisez la chaîne JSON en un objet JavaScript
      return JSON.parse(userJSON);
    }
    return null;
  }

  public setUser = (user: any): void => {
    // Sérialisez l'objet utilisateur en JSON
    const userJSON = JSON.stringify(user);
    // Stockez la chaîne JSON dans le sessionStorage
    sessionStorage.setItem("user", userJSON);
  }

  // Méthode pour effectuer la déconnexion de l'utilisateur
  public doLogout = (): void => {
    // Suppression du token et de l'utilisateur de la session de stockage
    let removeToken: void = sessionStorage.removeItem('token');
    let removeUser: void = sessionStorage.removeItem('user');
    this.router.navigate([""]);

  }

  // REQUETE HTTP / CRUD 
  public loggedIn = (formAuth: NgForm) => {
    console.log(formAuth.value)
    return this.http.post<any>(environment.API_URL + "authorize", {
      username: formAuth.value.username,
      password: formAuth.value.password
    });
  }

  public registration = (formRegister: NgForm) => {
    return this.http.post<any>(environment.API_URL + "register", formRegister.value);
  }
}
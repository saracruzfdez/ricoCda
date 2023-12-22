import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(protected authService: AuthService){}

  loggedIn = (formAuth: NgForm) => {
    // POST :  Si l'utilisateur est enregistrer dans la base de données le serveur lui renverra un token
    // a chaque fois que l'on souhaite acceder a une route privée on dois envoyer le token
    // le serveur decode le token verifie la validité de la signature puis renvoie les infos demandées
    if (formAuth.valid) {
      this.authService.loggedIn(formAuth).subscribe({
        next: (data) => {
          // console.log('Utilisateur: ', data.user);
          // console.log('token: ', data.token);
           console.log('token: ', data);
          // Enregistre le token et redirige vers la page d'acceuil
          this.authService.doLogged(data);
        },
        error: (errors :Error) => {
          // console.log('Observer got an error: ' + err);
          // Gestion des erreurs de validation provenat de l'API
        },
        complete: () => console.log('Observer got a complete notification')
      });
    }
  }
}

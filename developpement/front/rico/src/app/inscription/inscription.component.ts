import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent {

  constructor(protected authService: AuthService){}

  registration = (formRegister: NgForm) => {
    // POST :  findAll
    console.log(formRegister.value);
    if (formRegister.valid) {
      this.authService.registration(formRegister).subscribe({
        next: (data) => {
          // console.log('Utilisateur: ', data.user);
          // console.log('token: ', data.token);
          // Enregistre le token et redirige vers la page d'acceuil
          this.authService.doLogged(data);
        },
        error: (errors: Error) => {
          console.log('Observer got an error: ', errors);
        },
        complete: () => console.log('Observer got a complete notification')
      });
    }
  }





}

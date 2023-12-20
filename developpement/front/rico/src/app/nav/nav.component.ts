import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {
  constructor(protected authService: AuthService) { }
// Deconnexion
logout(){
  this.authService.doLogout();
}

}



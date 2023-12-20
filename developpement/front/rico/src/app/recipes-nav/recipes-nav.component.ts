import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-recipes-nav',
  templateUrl: './recipes-nav.component.html',
  styleUrls: ['./recipes-nav.component.css']
})
export class RecipesNavComponent {
  @Input() isEditing: boolean = false;
  @Input() formTitle: string = 'Nouvelle recette';

  constructor(private router: Router, protected authService: AuthService) { }

  isActive(route: string): boolean {
    return this.router.isActive(route, true) && (!this.isEditing || this.router.url.includes('/recipes-form'));
  }
}

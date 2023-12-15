import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipes-nav',
  templateUrl: './recipes-nav.component.html',
  styleUrls: ['./recipes-nav.component.css']
})
export class RecipesNavComponent {
  @Input() isEditing: boolean = false;
  @Input() formTitle: string = 'Nouvelle recette';

  constructor(private router: Router) { }

  isActive(route: string): boolean {
    return this.router.isActive(route, true) && (!this.isEditing || this.router.url.includes('/recipes-form'));
  }
}

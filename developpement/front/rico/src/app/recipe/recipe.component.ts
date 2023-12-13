import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})

export class RecipeComponent implements OnInit {

  id:any;
  recipe:any;

  constructor(private recipeService: RecipeService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
  
    if (this.id !== null) {
  
    this.recipeService.getById(this.id)?.pipe(
      catchError((error) => {
        console.error('Error getting recipe data:', error);
        // Puedes realizar acciones adicionales en caso de error, si es necesario.
        return throwError('Error getting recipe data');
      })
    ).subscribe((recipeData) => {
      // Verificar si imagePath es una cadena antes de realizar el reemplazo
      if (typeof recipeData.image_path === 'string') {
        recipeData.image_path = recipeData.image_path.replace(/\\/g, '/');
      }
      this.recipe = recipeData;
      console.log("Recipe détails:", this.recipe);
    });
    } else {
      console.error('Id null');
    }
  }
}
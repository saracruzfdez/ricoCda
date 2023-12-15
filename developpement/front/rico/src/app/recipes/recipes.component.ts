import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe.service';


@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

recipes:any
isEditing: boolean = false;

constructor(private recipeService: RecipeService) { }

ngOnInit() {
  this.recipeService.getAll().subscribe(data => {
    console.log("Getting the data :", data);

    // Normalizing URLs in image_path property
    this.recipes = data.map((recipe: any) => ({
      ...recipe,
      image_path: recipe.image_path ? recipe.image_path.replace(/\\/g, '/') : undefined
    }));

    console.log("Getting data after replace:", this.recipes);

  });
  
  
}

}


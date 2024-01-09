import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../services/recipe.service';
import { JwtTokenService } from '../services/jwt-token.service';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-own-recipes',
  templateUrl: './own-recipes.component.html',
  styleUrls: ['./own-recipes.component.css']
})
export class OwnRecipesComponent implements OnInit {

  userRecipes: any
  isEditing: boolean = false;
  userId!: number | null;

  constructor(private recipeService: RecipeService, private jwtService: JwtTokenService, private authService: AuthService) { }

  ngOnInit() {


    const user = this.authService.getUser();
    console.log('User ID:', user);
  
    if (!user) {
      console.error("UserId no encontrado");
      return;
    }
  
    this.recipeService.getAllForCurrentUser(user.id).subscribe(
      data => {
        console.log("Recetas del usuario obtenidas correctamente:", data);
// Normalizing URLs in image_path property
this.userRecipes = data.map((recipe: any) => ({
  ...recipe,
  image_path: recipe.image_path ? recipe.image_path.replace(/\\/g, '/') : undefined
}));
console.log("Getting data after replace:", this.userRecipes);      },
      error => {
        console.error("Error al obtener recetas del usuario:", error);
      }
    );


  }
}
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponent } from './recipes/recipes.component';
import { MenusComponent } from './menus/menus.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { OwnRecipesComponent } from './own-recipes/own-recipes.component';
import { RecipesFormComponent } from './recipes-form/recipes-form.component';
import { RecipeComponent } from './recipe/recipe.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'recipes', component:RecipesComponent, canActivate: [authGuard]},
  {path:'menus', component:MenusComponent, canActivate: [authGuard]},
  {path:'shopping-list', component:ShoppingListComponent, canActivate: [authGuard]},
  {path:'own-recipes', component:OwnRecipesComponent, canActivate: [authGuard]},
  {path:'recipes-form', component:RecipesFormComponent, canActivate: [authGuard]},
  {path:'recipes-form/:id', component:RecipesFormComponent, canActivate: [authGuard]},
  {path:'recipe/:id', component:RecipeComponent, canActivate: [authGuard]},
  {path:'inscription', component:InscriptionComponent},
  {path:'login', component:LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

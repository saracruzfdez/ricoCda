import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponent } from './recipes/recipes.component';
import { MenusComponent } from './menus/menus.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { OwnRecipesComponent } from './own-recipes/own-recipes.component';
import { RecipesFormComponent } from './recipes-form/recipes-form.component';
import { RecipeComponent } from './recipe/recipe.component';

const routes: Routes = [
  {path:'recipes', component:RecipesComponent},
  {path:'menus', component:MenusComponent},
  {path:'shopping-list', component:ShoppingListComponent},
  {path:'own-recipes', component:OwnRecipesComponent},
  {path:'recipes-form', component:RecipesFormComponent},
  {path:'recipes-form/:id', component:RecipesFormComponent},
  {path:'recipe/:id', component:RecipeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

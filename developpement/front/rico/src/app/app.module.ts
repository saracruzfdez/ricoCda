import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { RecipesComponent } from './recipes/recipes.component';
import { MenusComponent } from './menus/menus.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { OwnRecipesComponent } from './own-recipes/own-recipes.component';
import { RecipesNavComponent } from './recipes-nav/recipes-nav.component';
import { RecipesFormComponent } from './recipes-form/recipes-form.component';
import { RecipeComponent } from './recipe/recipe.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    RecipesComponent,
    MenusComponent,
    ShoppingListComponent,
    OwnRecipesComponent,
    RecipesNavComponent,
    RecipesFormComponent,
    RecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

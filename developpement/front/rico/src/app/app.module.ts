import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { RecipesComponent } from './recipes/recipes.component';
import { MenusComponent } from './menus/menus.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { OwnRecipesComponent } from './own-recipes/own-recipes.component';
import { RecipesNavComponent } from './recipes-nav/recipes-nav.component';
import { RecipesFormComponent } from './recipes-form/recipes-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    RecipesComponent,
    MenusComponent,
    ShoppingListComponent,
    OwnRecipesComponent,
    RecipesNavComponent,
    RecipesFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

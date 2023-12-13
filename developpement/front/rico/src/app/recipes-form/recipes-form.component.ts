import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Recipe, RecipeService } from '../services/recipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipes-form',
  templateUrl: './recipes-form.component.html',
  styleUrls: ['./recipes-form.component.css']
})

export class RecipesFormComponent implements OnInit {

  recipesForm: FormGroup;

  constructor(private recipeService: RecipeService, private router: Router, private fb: FormBuilder) {
    this.recipesForm = this.fb.group({
      image_path: ['', Validators.required],
      category: ['', Validators.required],
      title: ['', Validators.required],
      prep_time: [null, Validators.required],
      cook_time: [null, Validators.required],
      persons_number: [null, Validators.required],
      difficulty: ['', Validators.required],
      average_cost: ['', Validators.required],
      country_origin: ['', Validators.required],
      ingredients: this.fb.array([this.createIngredient()]),
      steps: this.fb.array([this.createStep()])
    });
  }

  ngOnInit(): void {
  }

  get ingredients() {
    return this.recipesForm.get('ingredients') as FormArray | null;
  }

  get steps() {
    return this.recipesForm.get('steps') as FormArray | null;
  }

  createIngredient(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      quantity: [null, Validators.required],
      unit: ['', Validators.required]
    });
  }

  createStep(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  addIngredient() {
    const ingredients = this.ingredients;
    if (ingredients) {
      ingredients.push(this.createIngredient());
    }
  }

  addStep() {
    const steps = this.steps;
    if (steps) {
      steps.push(this.createStep());
    }
  }

  submitForm() {
    const recipesForm = this.recipesForm;
    if (!recipesForm) {
      console.error('recipesForm is null');
      return;
    }

    const ingredients = recipesForm.get('ingredients');
    const steps = recipesForm.get('steps');

    if (!ingredients || !steps || !recipesForm.valid) {
      console.error('Invalid form or null properties');
      return;
    }

    console.log(recipesForm.value as Recipe);

    this.recipeService.add(recipesForm.value as Recipe).subscribe(
      () => {
        this.router.navigate(['/recipes']);
      },
      (error) => {
        console.error('Error adding recipe:', error);
      }
    );
  }
}
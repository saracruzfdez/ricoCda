// recipes-form.component.ts

import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Recipe, RecipeService } from '../services/recipe.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CategoryEnum, CategoryService } from '../services/category.service';
import { AverageCostEnum, CostService } from '../services/cost.service';
import { DifficultyEnum, DifficultyService } from '../services/difficulty.service';
import { UnitEnum, UnitService } from '../services/unit.service';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-recipes-form',
  templateUrl: './recipes-form.component.html',
  styleUrls: ['./recipes-form.component.css']
})

export class RecipesFormComponent implements OnInit {

  recipesForm: FormGroup;
  categories: (CategoryEnum | string)[] = [];
  costs: (AverageCostEnum | string)[] = [];
  difficulties: (DifficultyEnum | string)[] = [];
  unities: (UnitEnum | string)[] = [];
  isEditing: boolean = false;
  recipeId: number | null = null;
  formTitle: string = 'Nouvelle recette';  // Título predeterminado

  constructor(
    private recipeService: RecipeService,
    private categoryService: CategoryService,
    private costService: CostService,
    private difficultyService: DifficultyService,
    private unitService: UnitService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {

    this.recipesForm = this.fb.group({
      image_path: ['', Validators.required],
      category: [null, Validators.required],
      title: ['', Validators.required],
      prep_time: [null, Validators.required],
      cook_time: [null, Validators.required],
      persons_number: [null, Validators.required],
      difficulty: [null, Validators.required],
      average_cost: [null, Validators.required],
      country_origin: ['', Validators.required],
      ingredients: this.fb.array([this.createIngredient()]),
      steps: this.fb.array([this.createStep()])
    });

    // Verifying if we are on edition version
    this.isEditing = route.snapshot.params['id'] !== undefined;
    if (this.isEditing) {
      // Catch the id in url params and charge details
      this.recipeId = +route.snapshot.params['id'];
      this.loadRecipeDetails(this.recipeId);
    }
  }

  ngOnInit(): void {

    this.categoryService.getCategories().subscribe(
      (data) => {
        this.categories = data;
      },
      (error) => {
        console.error('Error fetching categories', error);
      }
    );

    this.costService.getCosts().subscribe(
      (data) => {
        this.costs = data;
      },
      (error) => {
        console.error('Error fetching costs', error);
      }
    );

    this.difficultyService.getDifficulties().subscribe(
      (data) => {
        this.difficulties = data;
      },
      (error) => {
        console.error('Error fetching difficulties', error);
      }
    );

    this.unitService.getUnities().subscribe(
      (data) => {
        this.unities = data;
      },
      (error) => {
        console.error('Error fetching unities', error);
      }
    );
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
      unit: [null, Validators.required]
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

  loadRecipeDetails(recipeId: number): void {
    this.recipeService.getById(recipeId)?.subscribe(
      (data) => {
        this.loadRecipeForm(data);
        this.formTitle = 'Modifier la recette';
      },
      (error) => {
        console.error('Error fetching recipe details', error);
      }
    );
  }

  loadRecipeForm(recipe: Recipe): void {
    this.recipesForm.patchValue(recipe);

    const ingredientsFormArray = this.recipesForm.get('ingredients') as FormArray;
    ingredientsFormArray.clear();

    for (const ingredient of recipe.ingredients) {
      ingredientsFormArray.push(this.fb.group({
        name: [ingredient.name, Validators.required],
        quantity: [ingredient.quantity, Validators.required],
        unit: [ingredient.unit, Validators.required]
      }));
    }

    const stepsFormArray = this.recipesForm.get('steps') as FormArray;
    stepsFormArray.clear();

    for (const step of recipe.steps) {
      const existingStepIndex = stepsFormArray.controls.findIndex((control: AbstractControl) => (control as FormGroup)?.get('name')?.value === step.name);

      if (existingStepIndex !== -1) {
        stepsFormArray.at(existingStepIndex).patchValue({
          name: step.name,
          description: step.description
        });
      } else {
        stepsFormArray.push(this.fb.group({
          name: [step.name, Validators.required],
          description: [step.description, Validators.required]
        }));
      }
    }
  }

  submitted = false;

  submitForm() {
    this.submitted = true;

    const recipesForm = this.recipesForm;

    if (!recipesForm || !recipesForm.valid) {
      console.error('Invalid form or null properties');
      return;
    }

    if (this.isEditing && this.recipeId !== null) {
      this.recipeService.update(this.recipeId, recipesForm.value as Recipe).subscribe(
        () => {
          this.router.navigate(['/recipes']);
        },
        (error) => {
          console.error('Error updating recipe:', error);
        }
      );
    } else {
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
}

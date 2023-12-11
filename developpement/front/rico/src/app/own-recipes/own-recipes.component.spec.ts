import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnRecipesComponent } from './own-recipes.component';

describe('OwnRecipesComponent', () => {
  let component: OwnRecipesComponent;
  let fixture: ComponentFixture<OwnRecipesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OwnRecipesComponent]
    });
    fixture = TestBed.createComponent(OwnRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

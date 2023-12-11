import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipesNavComponent } from './recipes-nav.component';

describe('RecipesNavComponent', () => {
  let component: RecipesNavComponent;
  let fixture: ComponentFixture<RecipesNavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecipesNavComponent]
    });
    fixture = TestBed.createComponent(RecipesNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

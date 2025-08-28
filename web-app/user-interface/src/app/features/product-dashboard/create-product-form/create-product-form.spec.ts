import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateProductForm } from './create-product-form';

describe('CreateProductForm', () => {
  let component: CreateProductForm;
  let fixture: ComponentFixture<CreateProductForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateProductForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateProductForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ProductService} from '../../../services/product-service/product-service';
import {AuthService} from '../../../services/auth-service/auth-service';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-create-product-form',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './create-product-form.html',
  styleUrl: './create-product-form.css'
})
export class CreateProductForm {

  productForm: FormGroup;   // formulário reativo
  successMessage = '';       // mensagem de sucesso
  errorMessage = '';         // mensagem de erro

  constructor(
    private fb: FormBuilder,
    private produtcService: ProductService,
    private router: Router
  ) {
    this.productForm = this.fb.group({
      name:['', [Validators.required, Validators.minLength(4)]],
      category:['', [Validators.required, Validators.minLength(2)]],
      price: ['', [Validators.required, Validators.minLength(2)]],
      quantity:['', [Validators.required, Validators.minLength(2)]],
    })
  }

  onSubmit(){
    if (this.productForm.valid) {
      this.produtcService.createProduct(this.productForm.value).subscribe({
        next: () => {
          this.successMessage = 'Criação efetuada com sucesso!';
          this.errorMessage = '';
          this.productForm.reset();
        },
        error: (err) => {
          this.errorMessage = err.message; // vem do backend
          this.successMessage = '';
          console.error(err);
        }
      })
    }
  }
}

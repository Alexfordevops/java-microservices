import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {AuthService} from '../../../services/auth-service/auth-service';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './register-form.html',
  styleUrl: './register-form.css'
})
export class RegisterForm {

  registerForm: FormGroup;   // formulário reativo
  successMessage = '';       // mensagem de sucesso
  errorMessage = '';         // mensagem de erro

  //Constroi o formulario
  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.registerForm = this.fb.group({
      username:['', [Validators.required, Validators.minLength(5)]],
      name: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]]
    })
  }

  onSubmit(){
    if(this.registerForm.valid){
      this.authService.register(this.registerForm.value).subscribe({
        next: () => {
          this.successMessage = 'Usuário registrado com sucesso!';
          this.errorMessage = '';
          this.registerForm.reset(); // limpa o formulário
        },
        error: (err) => {
          this.errorMessage = 'Erro ao registrar usuário!';
          this.successMessage = '';
          console.error(err);
        }
      })
    }
  }
}

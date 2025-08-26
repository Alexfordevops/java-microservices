import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {AuthService} from '../../../services/auth-service/auth-service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css'
})
export class LoginForm {

  registerForm: FormGroup;   // formulário reativo
  successMessage = '';       // mensagem de sucesso
  errorMessage = '';         // mensagem de erro

  //Constroi o formulario de login
  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.registerForm = this.fb.group({
      username:['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  onSubmit(){
    if(this.registerForm.valid){
      this.authService.login(this.registerForm.value).subscribe({
        next: () => {
          this.successMessage = 'Login efetuado com sucesso!';
          this.errorMessage = '';
          this.registerForm.reset(); // limpa o formulário
        },
        error: (err) => {
          this.errorMessage = 'Erro ao efetuar o login!';
          this.successMessage = '';
          console.error(err);
        }
      })
    }
  }

}

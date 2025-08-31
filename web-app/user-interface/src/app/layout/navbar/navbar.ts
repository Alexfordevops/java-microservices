import { Component} from '@angular/core';
import {AuthService} from '../../services/auth-service/auth-service';
import {Router, RouterModule} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterModule,
  ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar{

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  logout():void{
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  goToRegister(){
    this.router.navigate(['/register']);
  }

  goToProducts(){
    this.router.navigate(['/products']);
  }
}

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService, User } from 'src/app/core/services/api-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User | undefined;

  constructor(private apiService: ApiService, private router:Router) {
    localStorage.clear();
  }

  Login = new FormGroup({
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required)
  });

  ngOnInit(): void {
  }

  goToRegister() {
    this.router.navigate(['/auth/register']);
  }

  goToForgotPassword() {
    this.router.navigate(['/auth/forgot-password']);
  }

  goToMainPage(){
    this.router.navigate(['/main-page']);
  }

  ProceedLogin() {
    if (this.Login.valid) {
      // Implementați logica de autentificare aici
      console.log('Autentificare cu succes', this.Login.value);
      this.goToMainPage();
    }
    
  }


}

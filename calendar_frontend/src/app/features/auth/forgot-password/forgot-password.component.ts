import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/core/services/auth-service.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss'
})
export class ForgotPasswordComponent {

  constructor(private router:Router, private authService:AuthServiceService) {  
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.ForgotPassword.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  ForgotPassword = new FormGroup({
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    checkPassword: new FormControl("", [Validators.required, this.confirmationValidator]),
  });

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.ForgotPassword.controls['checkPassword'].updateValueAndValidity());
  }

  saveNewPassword() {
    // if (this.ForgotPassword.valid) {
    //   this.authService.UpdatePassword(this.ForgotPassword.value).subscribe(
    //     response => {
    //       console.log("Inregistrare cu succes:", response);
    //       alert("Parola a fost salvata cu succes!");
    //       this.router.navigate(['/auth/login']);
    //     },
    //     error => {
    //       console.error(error);
    //       alert("Nume de utilizator invalid!");
    //     }
    //   );
    // } else {
    //   console.log(this.ForgotPassword.value);
    //   alert("Formular invalid!");
    //     }
  }

  ngOnInit(): void {
  }
}

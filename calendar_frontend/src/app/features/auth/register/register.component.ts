import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
import { AuthServiceService } from 'src/app/core/services/auth-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  captchaTooltipIcon: NzFormTooltipIcon = {
    type: 'info-circle',
    theme: 'twotone'
  };

  constructor(private router:Router, private authService:AuthServiceService) { }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.Register.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return {};
  };
  
  Register = new FormGroup({
    firstName: new FormControl("", Validators.required),
    lastName: new FormControl("", Validators.required),
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    checkPassword: new FormControl("",[Validators.required, this.confirmationValidator]),
    emailAddress: new FormControl("", [Validators.required, Validators.email]),
    phoneNo:new FormControl("",[Validators.required,Validators.pattern("^[0-9]*$")])
  });

  RegisterUser(): void {
    // if (this.Register.valid) {
    //   this.authService.RegisterUser(this.Register.value).subscribe(
    //     response => {
    //       console.log("Inregistrare cu succes:", response);
    //       alert("Inregistrare cu succes!");
    //       this.router.navigate(['/auth/login']);
    //     },
    //     error => {
    //       console.error(error);
    //       alert("Numele de utilizator exista deja. Introduceti altul!");
    //     }
    //   );
    // } else {
    //   console.log(this.Register.value);
    //   alert("Formular invalid!");
    //     }
    this.goToLogIn();
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.Register.controls['checkPassword'].updateValueAndValidity());
  }

  getCaptcha(e: MouseEvent): void {
    e.preventDefault();
  }

  goToLogIn(){
    this.router.navigate(['/auth/login']);
  }

  ngOnInit(): void {
  }

}

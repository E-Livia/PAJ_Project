import { Injectable } from '@angular/core';
import { ApiService } from './api-service';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthServiceService {
  readonly APIUrl="";

  constructor(private apiService:ApiService, private router:Router){}
}
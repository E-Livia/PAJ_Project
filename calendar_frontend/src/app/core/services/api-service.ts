import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

export interface User {
    username: string;
    // adaugă alte câmpuri necesare
  }

@Injectable({
  providedIn: 'root'
})
export class ApiService {
    private apiUrl = `${environment.apiUrl}/user`;

    constructor(private http: HttpClient) {}
  
    
  }
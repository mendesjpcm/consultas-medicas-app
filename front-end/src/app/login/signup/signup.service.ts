import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignUp } from '../../shared/models/signup.model';
import { Observable } from 'rxjs';
import { config } from 'src/app/shared/config';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(
    private http: HttpClient
  ) { }

  public signUp(signUp: SignUp): Observable<SignUp> {
    return this.http.post<any>(`${config.apiUrl}/user/signup`, signUp);
  }
}

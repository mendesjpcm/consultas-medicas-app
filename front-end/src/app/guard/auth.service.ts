import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { config } from '../shared/config';
import { Tokens } from '../shared/models';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly JWT_TOKEN = 'JWT_TOKEN';

  constructor(private http: HttpClient) { }

  login(user: { username: string}): Observable<HttpResponse<object>> {
    this.doLogoutUser();
    const passUser = {username: user.username};
    return this.http.post<HttpResponse<object>>(`${config.apiUrl}/auth/login`, passUser, { observe: 'response' })
    .pipe(
      tap((response: HttpResponse<any>) => {
          const tk = {
            jwt: response.headers.get('Authorization')
          };
          this.doLoginUser(user.username, tk);
      },
      ),
    );
  }

  private doLoginUser(username: string, tokens: Tokens) {
    this.storeTokens(tokens);
  }

  private doLogoutUser() {
    this.removeTokens();
  }

  public logout(){
    this.doLogoutUser();
  }

  private removeTokens() {
    localStorage.removeItem(this.JWT_TOKEN);
  }

  private storeTokens(tokens: Tokens) {
    localStorage.setItem(this.JWT_TOKEN, tokens.jwt);
  }

  getJwtToken() {
    return localStorage.getItem(this.JWT_TOKEN);
  }
}

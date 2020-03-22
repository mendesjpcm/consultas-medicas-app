import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpErrorResponse, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { BehaviorSubject, throwError, Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { catchError, switchMap, filter, take } from 'rxjs/operators';

@Injectable()
export class Interceptor implements HttpInterceptor{
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  private redirectUrl;
   constructor(
    public authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute,
   ){}

   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authService.getJwtToken()) {
      request = this.addToken(request, this.authService.getJwtToken());
    }

    return next.handle(request)
      .pipe(
        catchError( (error) => {
          if (error instanceof HttpErrorResponse) {
            if (error.error instanceof ErrorEvent) {
            } else {
                switch (error.status) {
                    case 401: // login
                    this.authService.logout();
                    this.router.navigate(['/login']);
                    setTimeout(() => {
                        this.toastr.clear();
                        this.toastr.warning('Você não está logado', 'Erro ao acessar');
                      }, 100);
                    break;
                    case 403:     // forbidden
                      break;
                }
          }
        }
          return throwError(error);
     })
      );
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `BEARER=${token}`
      }
    });
  }

}

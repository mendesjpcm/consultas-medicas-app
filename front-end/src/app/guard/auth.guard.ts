import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Constants } from '../shared/constants/constants';
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  jwt = new JwtHelperService();
  redirectUrl;

  constructor(
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute,
    private constants: Constants,
  ){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.checkLogin(next)){
        return true;
      }
      return false;
  }

  private checkLogin(route: ActivatedRouteSnapshot): boolean {

    const redirectUrl = route['_routerState']['url'];
    this.route.queryParams
      .subscribe(
        (success) => {
          this.redirectUrl = success.redirectUrl;
        }
      );

    if (this.authService.getJwtToken()
      && !this.jwt.isTokenExpired(this.authService.getJwtToken())
    ) {
      return true;
    }
    this.router.navigateByUrl(
      this.router.createUrlTree(
        ['/login'], {
        queryParams: {
          redirectUrl
        }
      }
      )
    );
    return false;
  }
}

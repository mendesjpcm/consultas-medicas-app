import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../guard/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Constants } from '../shared/constants/constants';
import { LoadingViewAppService } from '../shared/services/loading-view-app-service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  jwt = new JwtHelperService();
  signUpView = false;
  redirectUrl;
  formLogin = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.email]),
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private constants: Constants,
    private loadingView: LoadingViewAppService,
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      (success) => {
        this.redirectUrl = success.redirectUrl;
     });
    if (this.authService.getJwtToken() && !this.jwt.isTokenExpired(this.authService.getJwtToken())) {
      this.redirectUrl && !this.redirectUrl === null && !this.redirectUrl === undefined
                  ? this.router.navigate([this.redirectUrl]) : this.router.navigate([this.constants.PATH_HOME]);
    }
  }

  doLogin(): void {
    this.loadingView.setLoadingState(true);
    const user = {
      username: this.formLogin.controls.username.value
    };
    this.authService.login(user).subscribe(
      (success) => {
        setTimeout(() => {
          this.loadingView.setLoadingState(false);
          this.redirectUrl && !this.redirectUrl === null && !this.redirectUrl === undefined
                  ? this.router.navigate([this.redirectUrl]) : this.router.navigate([this.constants.PATH_HOME]);
        }, 1000);
    },
    (err) => {
      this.loadingView.setLoadingState(false);
      this.getErrorLogin(err);
    });
  }

  private getErrorLogin(error) {
    if (error.error.message){
      this.toastr.error(this.constants.TOAST_MSG_ERROR_LOGIN, this.constants.DICTIONARY[error.error.errors]);
    } else {
      this.toastr.error(this.constants.TOAST_MSG_FAIL_SERVER_COMMUNICATION);
    }
  }

  getErrorMessage(input): string {
    switch (input) {
      case 'username':
        return this.formLogin.controls.username.hasError('required') ? 'Campo obrigatório' :
            this.formLogin.controls.username.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' :
            this.formLogin.controls.username.hasError('email') ? 'Email inválido' : '';
    }
  }

  signUp(){
    this.signUpView = true;
  }

  backToLogin(){
    this.signUpView = false;
  }
}

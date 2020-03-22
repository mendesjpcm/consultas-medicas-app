import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Constants } from '../../shared/constants/constants';
import { LoadingViewAppService } from '../../shared/services/loading-view-app-service';
import { SignupService } from './signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  @Output() messageEvent = new EventEmitter();
  formLoginSignUp = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    email: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.email]),
  });
  constructor(
    private toastr: ToastrService,
    private constants: Constants,
    private loadingView: LoadingViewAppService,
    private signUpService: SignupService
  ) { }

  ngOnInit(): void {
  }

  doSignUp(): void {
    this.loadingView.setLoadingState(true);
    const obj = {
      id: 0,
      name: this.formLoginSignUp.controls.name.value,
      email: this.formLoginSignUp.controls.email.value
    };
    this.signUpService.signUp(obj).subscribe(
      (success) => {
        this.loadingView.setLoadingState(false);
        this.toastr.success(this.constants.TOAST_MSG_SUCCESS_SIGNUP);
        this.formLoginSignUp.reset();
      },
      (err) => {
        this.loadingView.setLoadingState(false);
        this.getErrorService(err);
      }
    );
  }

  private getErrorService(error) {
    if (error.error.message){
      this.toastr.error(this.constants.TOAST_MSG_ERROR_SIGNUP, this.constants.DICTIONARY[error.error.message]);
    } else {
      this.toastr.error(this.constants.TOAST_MSG_FAIL_SERVER_COMMUNICATION);
    }
  }

  getErrorMessage(input): string {
    switch (input) {
      case 'name':
        return this.formLoginSignUp.controls.name.hasError('required') ? 'Campo obrigatório' :
            this.formLoginSignUp.controls.name.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' : '';
      case 'email':
        return this.formLoginSignUp.controls.email.hasError('required') ? 'Campo obrigatório' :
            this.formLoginSignUp.controls.email.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' :
            this.formLoginSignUp.controls.email.hasError('email') ? 'Email inválido' : '';
    }
  }

  goBackToLogin(): void {
    this.messageEvent.emit();
  }

}

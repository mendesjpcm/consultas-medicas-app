import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToastrModule } from 'ngx-toastr';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './guard/interceptor';
import { LoadingViewAppComponent } from './shared/components/loading-view-app/loading-view-app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './shared/material/material.module';
import { Constants } from './shared/constants/constants';

@NgModule({
  declarations: [
    AppComponent,
    LoadingViewAppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      countDuplicates: true,
      resetTimeoutOnDuplicate: true,
      timeOut: 5000,
      closeButton: true,
      progressBar: true,
      positionClass: 'toast-bottom-full-width',
      maxOpened: 1
    }),
    HttpClientModule,
    MaterialModule
  ],
  exports: [
    MaterialModule
  ],
  providers: [
    Constants,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeRoutingModule } from './home-routing.module';
import { MenuComponent } from './template-parts/menu/menu.component';
import { HeaderComponent } from './template-parts/header/header.component';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { MaterialModule } from '../shared/material/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AuthGuard } from '../guard/auth.guard';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
@NgModule({
  declarations: [MenuComponent, HeaderComponent, HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    MaterialModule,
    FlexLayoutModule
  ],
  providers: [
    FlexLayoutModule,
    AuthGuard
  ]
})
export class HomeModule { }

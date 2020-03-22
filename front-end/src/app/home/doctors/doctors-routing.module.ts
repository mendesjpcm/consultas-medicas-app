import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoctorsComponent } from './doctors.component';

const doctorsRoutes: Routes = [
  {
    path: '',
    component: DoctorsComponent,
  }
];


@NgModule({
  imports: [RouterModule.forChild(doctorsRoutes)],
  exports: [RouterModule]
})
export class DoctorsRoutingModule { }

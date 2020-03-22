import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppointmentsComponent } from './appointments.component';

const appointmentsRoutes: Routes = [
  {
    path: '',
    component: AppointmentsComponent,
  }
];


@NgModule({
  imports: [RouterModule.forChild(appointmentsRoutes)],
  exports: [RouterModule]
})
export class AppointmentsRoutingModule { }

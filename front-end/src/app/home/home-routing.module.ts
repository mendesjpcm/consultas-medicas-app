import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Constants } from '../shared/constants';
import { HomeComponent } from './home.component';
import { AuthGuard } from '../guard/auth.guard';

const homeRoutes: Routes = [{
  path: Constants.PATH_HOME,
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: Constants.PATH_EMPTY,
        redirectTo: Constants.PATH_APPOINTMENT,
      },
      {
        path: Constants.PATH_APPOINTMENT,
        loadChildren: () => import('./appointments/appointments.module').then(m => m.AppointmentsModule),
        canActivate: [AuthGuard]
      },
      {
        path: Constants.PATH_DOCTORS,
        loadChildren: () => import('./doctors/doctors.module').then(m => m.DoctorsModule),
        canActivate: [AuthGuard]
      }
    ],
  },
  {
    path: '**',
    redirectTo: Constants.PATH_HOME
  }
];

@NgModule({
  imports: [RouterModule.forChild(homeRoutes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

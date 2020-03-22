import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Constants } from './shared/constants';
import { AuthGuard } from './guard/auth.guard';

const appRoutes: Routes = [
  {
    path: Constants.PATH_LOGIN,
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
  },
  {
    path: Constants.PATH_HOME,
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule),
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: Constants.PATH_HOME
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

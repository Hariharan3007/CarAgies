import { Routes } from '@angular/router';
import { SignupComponent } from './signup/signup';
import { LoginComponent } from './login/login';
import { VerifyCodeComponent } from './verify-code/verify-code';
import { ForgotPasswordComponent } from './forgot-password/forgot-password';
import { DashboardComponent } from './dashboard/dashboard';
import { AddCarComponent } from './add-car/add-car';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'verify-code', component: VerifyCodeComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'add-car', component: AddCarComponent },
  { path: '**', redirectTo: 'login' },
];

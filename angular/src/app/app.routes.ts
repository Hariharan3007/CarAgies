import { Routes } from '@angular/router';
import { Signup } from './signup/signup';
import { Login} from './login/login';
import { VerifyCodeComponent } from './verify-code/verify-code';
import { ForgotPasswordComponent } from './forgot-password/forgot-password';
import { Dashboard } from './dashboard/dashboard';
import { AddCar } from './add-car/add-car';
import { ViewCar } from './view-car/view-car';
import { MyProfile } from './my-profile/my-profile';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'signup', component: Signup },
  { path: 'login', component: Login },
  { path: 'verify-code', component: VerifyCodeComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'dashboard', component: Dashboard },
  { path: 'add-car', component: AddCar },
  { path: 'view-car', component: ViewCar},
  { path: 'profile', component: MyProfile}
];

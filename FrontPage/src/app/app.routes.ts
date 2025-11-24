import { Routes } from '@angular/router';
import { Login } from './login/login';
import { UserDashboard } from './dashboards/user-dashboard/user-dashboard';
import { AdminDashboard } from './dashboards/admin-dashboard/admin-dashboard';
import { ServicerDashboard } from './dashboards/servicer-dashboard/servicer-dashboard';
import { ViewProfile } from './dashboards/user-dashboard/view-profile/view-profile';
import { Signup } from './signup/signup';
import { ViewMyCar } from './dashboards/user-dashboard/view-my-car/view-my-car';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'admin-dashboard', component: AdminDashboard },
  { path: 'user-dashboard', component: UserDashboard },
  { path: 'servicer-dashboard', component: ServicerDashboard },
  { path: 'view-profile', component: ViewProfile },
  { path: 'signup', component:Signup},
  { path: 'view-my-car', component:ViewMyCar }
];

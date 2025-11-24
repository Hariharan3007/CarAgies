import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  templateUrl: './user-dashboard.html',
  styleUrl: './user-dashboard.scss'
})
export class UserDashboard {

  constructor(private router: Router) {}
  viewProfile() {
    this.router.navigate(['view-profile']);
  }

  viewMyCar(){
    this.router.navigate(['view-my-car']);
  }
}

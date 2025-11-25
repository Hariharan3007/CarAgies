import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent {
  username: string | null = null;

  constructor(private router: Router, private auth: AuthService) {
    // try to read username from navigation state or token (if available)
    const nav = this.router.getCurrentNavigation?.();
    const state = nav?.extras?.state as any;
    if (state?.username) this.username = state.username;
  }

  addCar() {
    this.router.navigate(['/add-car']);
  }

  sentRequests() {
    this.router.navigate(['/sent-requests']);
  }

  viewCars() {
    this.router.navigate(['/view-car']);
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}

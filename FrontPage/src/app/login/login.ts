import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {

  username = '';
  password = '';
  role = '';
  message = '';

  constructor(private auth: AuthService, private router: Router) {}

  submitLogin() {
    const body = {
      username: this.username,
      password: this.password,
      role: this.role
    };

    this.auth.login(body).subscribe(
      (token) => {
        if (token === "Invalid Credentials") {
          return;
        }

        this.auth.saveToken(token);

        const role = this.auth.decodeRoleFromToken();

        if (role === "admin") this.router.navigate(['/admin-dashboard']);
        else if (role === "user") this.router.navigate(['/user-dashboard']);
        else if (role === "servicer") this.router.navigate(['/servicer-dashboard']);
        else this.message = "Unknown role!";
      },
      () => this.message = "Invalid Credentials"
    );
  }

  signup(){
    this.router.navigate(['/signup']);
  }
}

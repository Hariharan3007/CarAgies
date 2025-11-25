import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  loading = false;
  successMessage = '';
  errorMessage = '';

  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  submitLogin() {
    if (this.loginForm.invalid) {
      this.errorMessage = 'Please enter username and password.';
      console.warn('Form invalid:', this.loginForm.errors);
      return;
    }

    this.loading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        this.loading = false;
        this.successMessage = 'Login Successful!';
        console.log('Login success:', res);
        // navigate to dashboard after successful login
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 500);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'Invalid username or password.';
        console.error('Login error:', err);
        // Keep error visible for 3 seconds before clearing
        setTimeout(() => {
          this.errorMessage = '';
        }, 3000);
      }
    });
  }

  forgotPassword() {
    // kept for backward compatibility, but routing now handled by ForgotPassword page
    this.router.navigate(['/forgot-password']);
  }
}

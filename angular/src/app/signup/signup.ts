import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css'],
})
export class SignupComponent {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private auth = inject(AuthService);
  private router = inject(Router);

  signupForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(2)]],
    username: ['', [Validators.required, Validators.minLength(4)]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
    role: ['USER', [Validators.required]],
  });

  loading = false;
  serverMessage: string | null = null;
  serverError = false;

  get f() {
    return this.signupForm.controls;
  }

  submit() {
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.serverMessage = null;
    this.serverError = false;

    this.auth.signup(this.signupForm.value).subscribe({
      next: (res: string) => {
        this.loading = false;
        this.serverMessage = res;
        this.serverError = false;
        this.signupForm.reset();
        this.signupForm.patchValue({ role: 'USER' });
        setTimeout(() => this.router.navigate(['/login']), 800);
      },
      error: (err: any) => {
        this.loading = false;
        this.serverError = true;
        this.serverMessage = err?.error || 'Something went wrong. Please try again.';
      },
    });
  }
}

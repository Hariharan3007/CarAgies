import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './signup.html',
  styleUrls: ['./signup.scss']
})
export class Signup {
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  signupForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
    role: ['USER', Validators.required]
  });

  get f() {
    return this.signupForm.controls;
  }

  submit() {
    if (this.signupForm.invalid) return;

    this.http.post(
      "http://localhost:8080/user/signup",
      this.signupForm.value,
      { responseType: "text" }
    ).subscribe({
      next: () => this.router.navigate(['/login']),
      error: (err) => alert("Error: " + err.error)
    });
  }
}

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './forgot-password.html',
  styleUrls: ['./forgot-password.css']
})
export class ForgotPasswordComponent {
  form: FormGroup;
  loading = false;
  message: string | null = null;
  error = false;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {
    this.form = this.fb.group({ identifier: ['', Validators.required] });
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.message = null;
    this.error = false;

    const payload = { identifier: this.form.value.identifier };
    this.auth.requestOtp(payload).subscribe({
      next: (res: any) => {
        this.loading = false;
        this.message = typeof res === 'string' ? res : 'Verification code sent.';
        // navigate to verify-code with the identifier as email
        this.router.navigate(['/verify-code'], { state: { email: payload.identifier } });
      },
      error: (err) => {
        this.loading = false;
        this.error = true;
        this.message = err?.error || 'Failed to send verification code.';
      }
    });
  }
}

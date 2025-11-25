import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-verify-code',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './verify-code.html',
  styleUrls: ['./verify-code.css']
})
export class VerifyCodeComponent {
  form: FormGroup;
  loading = false;
  message: string | null = null;
  error = false;
  username: string | null = null;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {
    this.form = this.fb.group({
      email: ['', Validators.required],
      code: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
    });

    // Try to read username passed via navigation state
    const nav = this.router.getCurrentNavigation?.();
    const state = nav?.extras?.state as any;
    if (state?.username || state?.email || state?.identifier) {
      this.username = state.username || state.email || state.identifier;
      this.form.patchValue({ email: this.username });
    }
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.message = null;
    this.error = false;

    const fv = this.form.value;
    const payload = {
      email: fv.email,
      code: fv.code,
      newPassword: fv.newPassword,
    };

    this.auth.verifyCode(payload).subscribe({
      next: (res: any) => {
        this.loading = false;
        this.message = 'Password reset successfully. Redirecting...';
        // AuthService stores token in localStorage; navigate to dashboard
        setTimeout(() => this.router.navigate(['/dashboard']), 600);
      },
      error: (err) => {
        this.loading = false;
        this.error = true;
        // backend returns JSON error map or plain text
        const errMsg = err?.error?.error || err?.error || err?.message;
        this.message = errMsg || 'Verification failed. Please try again.';
      }
    });
  }
}

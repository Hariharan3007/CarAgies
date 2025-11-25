// src/app/services/auth.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

export interface SignupRequest {
  name: string;
  username: string;
  password: string;
  email: string;
  phone: number;
  role: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);

  // Use relative path so Angular dev-server proxy (proxy.conf.json) forwards to backend port
  private baseUrl = '/user';

  signup(data: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/signup`, data, {
      responseType: 'text', // backend returns String
    });
  }

  // Request an OTP for password reset. Backend exposes GET /user/otp?email=... returning plain text
  requestOtp(payload: any): Observable<string> {
    const identifier = payload?.identifier || payload?.email || payload?.username;
    // call backend GET /otp?email=<identifier>
    return this.http.get(`${this.baseUrl}/otp`, { params: { email: identifier }, responseType: 'text' });
  }

  // Verify the received code and set new password. Backend accepts { email, code, newPassword }
  // Backend responds with JSON { token: string }
  verifyCode(payload: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify-code`, payload).pipe(
      tap((res: any) => {
        let token: string | undefined;
        if (!res) return;
        if (typeof res === 'string') {
          token = res;
        } else if (res.token) {
          token = res.token;
        }
        if (token) {
          localStorage.setItem('auth_token', token);
        }
      })
    );
  }

  login(payload: LoginRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, payload, {
      responseType: 'text', // backend returns plain String token
    }).pipe(
      tap((res: any) => {
        let token: string | undefined;
        if (!res) return;
        if (typeof res === 'string') {
          token = res;
        } else if (res.token) {
          token = res.token;
        }
        if (token) {
          localStorage.setItem('auth_token', token);
        }
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  logout(): void {
    localStorage.removeItem('auth_token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  // Avoid injecting AuthService here to prevent a circular dependency
  // (AuthService -> HttpClient -> interceptors -> TokenInterceptor -> AuthService).
  // Read token directly from localStorage instead.
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    try {
      const token = localStorage.getItem('auth_token');
      if (token && token.trim()) {
        // Clone the request and add Authorization header
        const authReq = req.clone({
          setHeaders: {
            Authorization: `Bearer ${token.trim()}`,
          },
        });
        return next.handle(authReq);
      }
    } catch (e) {
      // accessing localStorage may throw in some environments; ignore and send request without token
      console.warn('Error reading token from localStorage:', e);
    }
    return next.handle(req);
  }
}

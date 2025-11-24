import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = "http://localhost:8080/user/login";

  constructor(private http: HttpClient) {}

  login(body: any): Observable<any> {
    return this.http.post(this.loginUrl, body, { responseType: 'text' });
  }

  saveToken(token: string) {
    localStorage.setItem("jwt", token);
  }

  getToken() {
    return localStorage.getItem("jwt");
  }

  decodeRoleFromToken(): string {
    const token = this.getToken();
    if (!token) return "";

    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.role;
  }

  signup(body: any) {
  return this.http.post("http://localhost:8080/user/signup", body);
}

checkUsername(username: string) {
  return this.http.get<boolean>("http://localhost:8080/user/check-username/" + username);
}

}

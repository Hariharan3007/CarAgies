import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './signup.html'
})
export class Signup {

  name = '';
  username = '';
  password = '';
  phone = '';
  role = '';

  usernameAvailable: boolean | null = null;
  usernameCheck$ = new Subject<string>();

  constructor(private auth: AuthService, private router: Router) {
    // Debounce username API calls
    this.usernameCheck$
      .pipe(debounceTime(500), distinctUntilChanged())
      .subscribe((username) => this.checkUsername(username));
  }

  // called when user types username
  onUsernameChange() {
    this.usernameAvailable = null;
    this.usernameCheck$.next(this.username);
  }

  checkUsername(username: string) {
    this.auth.checkUsername(username).subscribe(
      (res: boolean) => {
        this.usernameAvailable = res;
      },
      () => this.usernameAvailable = false
    );
  }

  submitSignup() {
    if (!this.usernameAvailable) {
      alert("Username already taken!");
      return;
    }

    const body = {
      name: this.name,
      username: this.username,
      password: this.password,
      phone: this.phone,
      role: this.role
    };

    this.auth.signup(body).subscribe(
      () => {
        alert("Signup successful!");
        this.router.navigate(['/login']);
      },
      () => alert("Signup failed!")
    );
  }
}

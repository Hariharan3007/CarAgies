import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  loading = false;
  successMessage = '';
  errorMessage = '';


  loginForm!: FormGroup;

  constructor(private fb:FormBuilder){
      this.loginForm = this.fb.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      })
  }
  http = inject(HttpClient);
  router = inject(Router)


  onLogin(){
    if(this.loginForm.invalid){
      this.errorMessage = 'Please enter Username and Password';
      return;
    }
    this.loading = true;
    const formValue = this.loginForm.value;
      this.http.post("http://localhost:8080/user/login", formValue, {responseType: 'text'}).subscribe({
        next: (res) =>{
          if(res){
            this.loading = false;
            this.successMessage = 'Login successful';
            alert("login successfully");
            localStorage.setItem("auth_token", res);
            setTimeout( () => {
                this.router.navigateByUrl("/dashboard");
            }, 2000);
            
          }
            console.log(res)
        }, error: () =>{
          this.loading = false;
          this.errorMessage = 'Invalid Credentials';
          setTimeout( () => {
            this.errorMessage = '';
          }, 3000);
            alert("Invalid Credentials");
        }
      })
  }


}

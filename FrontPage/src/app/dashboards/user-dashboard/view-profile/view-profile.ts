import { HttpClient, HttpParamsOptions } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-profile',
  standalone: true,
  imports: [],
  templateUrl: './view-profile.html',
  styleUrl: './view-profile.scss',
})
export class ViewProfile implements OnInit{
  ngOnInit(): void {
    
    this.getProfileDetails();
  }

  constructor(private http:HttpClient){
  }

  name = ' ';
  username =' ';
  email = ' ';
  phone = ' ';

  getProfileDetails() {
    this.http.get('http://localhost:8080/user/profile/view')
      .subscribe((res: any) => {
        this.name = res.name;
        this.username = res.username;
        this.email = res.email;
        this.phone = res.phone;
      });

  }  
}


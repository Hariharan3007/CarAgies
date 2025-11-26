import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-my-profile',
  imports: [],
  templateUrl: './my-profile.html',
  styleUrl: './my-profile.scss',
})
export class MyProfile implements OnInit {

  http = inject(HttpClient);
  location = inject(Location);

  name:any = '';
  username = '';
  email = '';
  phone = '';

  ngOnInit(): void {
    this.http.get("http://localhost:8080/user/profile/view")
      .subscribe({
        next: (res: any) =>{
            this.name = res.name;
            this.username = res.username;
            this.email = res.email;
            this.phone = res.phone;
            console.log(res);
        },
        error: () =>{

        }
      })
  }

  goBack() {
    this.location.back();   // navigates to last visited page
  }
}

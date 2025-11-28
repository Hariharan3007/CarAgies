import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router, RouterOutlet, RouterLinkWithHref } from "@angular/router";

@Component({
  selector: 'app-dashboard',
  imports: [RouterOutlet],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard {

  http = inject(HttpClient);
  router = inject(Router);

  viewMyProfile(){
    this.router.navigate(['profile'])
  }

  add_car(){
      this.router.navigateByUrl("add-car");
  }

  view_car(){
      this.router.navigateByUrl("view-car");
  }

  logout(){
    localStorage.removeItem('auth_token');
    this.router.navigate(['']);
  }

}

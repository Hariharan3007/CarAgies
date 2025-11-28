import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';

interface Car {
  vin: string;
  make: string;
  model: string;
  yearOfManufacture: number;
  fuelType: string;
}

@Component({
  selector: 'app-view-car',
  imports: [CommonModule],
  templateUrl: './view-car.html',
  styleUrl: './view-car.scss',
})
export class ViewCar implements OnInit{

   http = inject(HttpClient);
   location = inject(Location);

  cars: Car[] = [];
  loading = false;
  errorMessage: string | null = null;

  ngOnInit(): void {
    this.fetchCars();
  }

  fetchCars() {
    this.loading = true;
    this.errorMessage = null;

    this.http.get<Car[]>("http://localhost:8080/user/car/view")
      .subscribe({
        next: (res) => {
          this.cars = res;
          this.loading = false;
        },
        error: (err) => {
          this.loading = false;
          this.errorMessage = err?.error || 'Failed to load cars';
          console.error(err);
        }
      });
  }

   goBack() {
    this.location.back();   
  }

}

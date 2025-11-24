import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-view-my-car',
  imports: [CommonModule],
  templateUrl: './view-my-car.html',
  styleUrl: './view-my-car.scss',
})
export class ViewMyCar implements OnInit {

   constructor(private http:HttpClient){}

   cars: any[] = [];

  ngOnInit(): void {
    
    this.http.get<any>('http://localhost:8080/user/car/view')
    .subscribe(data => {
      this.cars = data;
    });
  }

  

 

  id = '';
  vin = 'dfdv';
  make = '';
  model = '';
  fuelType = '';
  yearOfManufacture = '';
  owner = '';

  viewCar(){

    this.http.get('http://localhost:8080/user/car/view')
    .forEach((res:any) => {
      this.id = res.id,
      console.log(this.id),
      this.vin = res.vin,
      this.make = res.make,
      this.model = res.model,
      this.fuelType = res.fuelType,
      this.yearOfManufacture = res.yearOfManufacture,
      this.owner = res.owner
    }
 
  );

  }

}

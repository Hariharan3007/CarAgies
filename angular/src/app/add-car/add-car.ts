import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-car',
  imports: [ReactiveFormsModule],
  templateUrl: './add-car.html',
  styleUrl: './add-car.scss',
})
export class AddCar {

  location = inject(Location);

  addForm: FormGroup = new FormGroup({
      vin : new FormControl(""),
      make : new FormControl(""),
      model : new FormControl(""),
      fuelType : new FormControl(""),
      yearOfManufacture : new FormControl("")
  });

  http = inject(HttpClient);

  add(){
      const carbody = this.addForm.value;

      this.http.post("http://localhost:8080/user/car/add", carbody, {responseType: 'text'})
      .subscribe({
        next: (res) =>{
          alert(res);
        },
        error: (error) =>{
          console.log(error);
        }
      })
  }

  goBack() {
    this.location.back();   // navigates to last visited page
  }

}

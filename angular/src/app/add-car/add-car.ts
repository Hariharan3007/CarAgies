import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CarService } from '../services/car.service';

@Component({
  selector: 'app-add-car',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-car.html',
  styleUrls: ['./add-car.css']
})
export class AddCarComponent {
  private fb = inject(FormBuilder);
  private carService = inject(CarService);
  router = inject(Router);

  addForm = this.fb.group({
    vin: ['', [Validators.required, Validators.minLength(5)]],
    make: ['', [Validators.required]],
    model: ['', [Validators.required]],
    fuelType: ['', [Validators.required]],
    yearOfManufacture: ['', [Validators.required, Validators.pattern(/^[0-9]{4}$/)]],
  });

  loading = false;
  serverMessage: string | null = null;
  serverError = false;

  get f() { return this.addForm.controls; }

  submit() {
    if (this.addForm.invalid) {
      this.addForm.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.serverMessage = null;
    this.serverError = false;

    this.carService.addCar(this.addForm.value).subscribe({
      next: (res: string) => {
        this.loading = false;
        this.serverMessage = res;
        this.serverError = false;
        // go back to dashboard after success
        setTimeout(() => this.router.navigate(['/dashboard']), 900);
      },
      error: (err: any) => {
        this.loading = false;
        this.serverError = true;
        this.serverMessage = err?.error || 'Failed to add car';
      }
    });
  }
}

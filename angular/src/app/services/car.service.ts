import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CarService {
  private http = inject(HttpClient);
  private base = '/user';

  addCar(car: any): Observable<string> {
    //return this.http.post(`${this.base}/car/add`, car, { responseType: 'text' });
    return this.http.post('http://localhost:8080/user/car/add', car, {responseType: 'text'});
  }
}

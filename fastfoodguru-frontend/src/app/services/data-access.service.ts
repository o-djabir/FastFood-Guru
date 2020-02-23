import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { Observable } from 'rxjs';
const RESTAURANTS_API_URL = 'http://localhost:5000/api/restaurants/';

@Injectable({
  providedIn: 'root'
})
export class DataAccessService {

  constructor(private client: HttpClient) { }
  getRestaurants() {
    this.client.get(`${RESTAURANTS_API_URL}`)
      .subscribe((data) => console.log(JSON.stringify(data)));
  }
}

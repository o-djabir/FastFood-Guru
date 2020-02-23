import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import {Restaurant, RestaurantAdapter} from '../core/restaurant.model';
const RESTAURANTS_API_URL = 'http://localhost:5000/api/restaurants/';
const RESTAURANTS_NAME_API_URL = 'http://localhost:5000/api/restaurants/names';

@Injectable()
export class RestaurantService {

    constructor (
        private http:HttpClient,
        private adapter: RestaurantAdapter
    ) {}

    getRestaurants(): Observable<Restaurant[]> {
        return this.http.get(`${RESTAURANTS_API_URL}`)
        .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
    }

    getSpecificRestaurant(name: String): Observable<Restaurant[]> {
        return this.http.get(`${RESTAURANTS_API_URL + name}`)
        .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
    }

    getRestaurantsNames(): Observable<string[]> {
        return this.http.get<string[]>(RESTAURANTS_NAME_API_URL);
    }
}
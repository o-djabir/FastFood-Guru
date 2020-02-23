import { Component, OnInit } from '@angular/core';
import {Restaurant} from '../core/restaurant.model';
import {RestaurantService} from '../services/RestaurantService';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  restaurants: Restaurant[];
  rating: Number;

  constructor(private restaurantService: RestaurantService) { 
    this.restaurants = [];
  }

  ngOnInit() {  
    var v = (<HTMLInputElement>document.getElementById("typeahead-input")).value;
    this.restaurantService.getSpecificRestaurant(v).subscribe((restaurants: Restaurant[]) => {
      this.restaurants = restaurants;
    });
  }

  getRestaurantId() {
    var id = 0;
    for (var i = 0; i < this.restaurants.length; i++) {
      id = this.restaurants[i].id;
    }
    return id;
  }

}

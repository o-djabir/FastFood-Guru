import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../services/RestaurantService';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  names: string[];
  search: any;
  canBeLoaded: boolean;

  constructor(private restaurantService: RestaurantService) {
    this.names = [];
  }

  ngOnInit() {
    this.restaurantService.getRestaurantsNames().subscribe(response => {
      this.names = response.map(item => item);
    });
    this.search = (text$: Observable<string>) =>
      text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => term.length < 1 ? []
          : this.names.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
      );
  }

  canILoadIt() {
    this.canBeLoaded = false;
    var v = (<HTMLInputElement>document.getElementById("typeahead-input")).value;
    for (var i = 0; i < this.names.length;i++) {
      if (v == this.names[i]) {
        this.canBeLoaded = true;
      }
    }
  }

  clear () {
    this.canBeLoaded = false;
    (<HTMLInputElement>document.getElementById("typeahead-input")).value = "";
  }

}

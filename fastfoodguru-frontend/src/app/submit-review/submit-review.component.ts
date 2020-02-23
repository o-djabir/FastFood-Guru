import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { ReviewService } from '../services/ReviewService';
import { ReviewPayload} from '../core/review.payload';
import {Restaurant} from '../core/restaurant.model';
import {RestaurantService} from '../services/RestaurantService';
import {RestaurantListComponent} from '../restaurant-list/restaurant-list.component';

@Component({
  selector: 'app-submit-review',
  templateUrl: './submit-review.component.html',
  styleUrls: ['./submit-review.component.css']
})
export class SubmitReviewComponent implements OnInit {

  isLoggedIn = false;
  restaurants: Restaurant[];
  review: ReviewPayload;
  contenu: String;
  note: number;

  constructor(private tokenStorage: TokenStorageService, private restaurantService: RestaurantService,
    private reviewService: ReviewService, private restoComponent: RestaurantListComponent) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
    this.note = 0;
  }

  onSubmit() {
    this.contenu = (<HTMLInputElement>document.getElementById("reviewContent")).value;
    var id = this.restoComponent.getRestaurantId();
    this.review = new ReviewPayload(this.contenu, id, this.note);
    console.log(this.review);
    this.reviewService.addReview(this.review);
    alert("Your review has been successfully submitted. Thank you!");
    window.location.reload();
  }

  ratingComponentClick(clickObj: any): void {
      this.note = clickObj.rating;
    }

}

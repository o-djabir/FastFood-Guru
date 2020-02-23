import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { TokenStorageService } from '../auth/token-storage.service';
import {Review, ReviewAdapter} from '../core/review.model';
import {ReviewPayload} from '../core/review.payload';
const REVIEWS_API_URL = 'http://localhost:5000/api/reviews/';
const RESTAURANTS_REVIEWS_API_URL = 'http://localhost:5000/api/reviews/restaurants';


const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

@Injectable()
export class ReviewService {

    constructor (
        private http:HttpClient,
        private adapter: ReviewAdapter,
        private tokenStorage: TokenStorageService
    ) {}

    getReviews(): Observable<Review[]> {
        return this.http.get(`${REVIEWS_API_URL}`)
        .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
    }
    getReviewsForRestaurant(id: any): Observable<Review[]> {
        return this.http.get(`${RESTAURANTS_REVIEWS_API_URL}/${id}`)
        .pipe(map((data: any[]) => data.map(item => this.adapter.adapt(item))));
    }
    addReview(r: ReviewPayload) {
        httpOptions.headers =
        httpOptions.headers.set('Authorization', this.tokenStorage.getToken());
        return this.http.post<ReviewPayload>(REVIEWS_API_URL, r, httpOptions).subscribe();
    }


}
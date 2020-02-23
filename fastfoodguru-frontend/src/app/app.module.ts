import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {RestaurantService} from './services/RestaurantService';
import {ReviewService} from './services/ReviewService';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserInfoComponent } from './user-info/user-info.component';
import { NgxTypeaheadModule } from 'ngx-typeahead';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { httpInterceptorProviders } from './auth/auth-interceptor';
import { SearchComponent } from './search/search.component';
import { SubmitReviewComponent } from './submit-review/submit-review.component';
import { RatingComponent } from './rating/rating.component';
@NgModule({
  declarations: [
    AppComponent,
    RestaurantListComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserInfoComponent,
    SearchComponent,
    SubmitReviewComponent,
    RatingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NgxTypeaheadModule,
    NgbModule,
  ],
  providers: [RestaurantService, ReviewService, httpInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [UserInfoComponent]
})
export class AppModule { }

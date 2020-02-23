import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';
import { UserService } from '../services/user.service';

interface User {
  name: String;
  username: String;
  email: String;
  role: String;
}

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  info: any;
  board: string;
  user: User;
  errorMessage: string;


  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.userService.getUserBoard().subscribe(
      data => {
        this.user = JSON.parse(data);
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}

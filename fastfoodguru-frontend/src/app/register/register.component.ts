import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { TokenStorageService } from '../auth/token-storage.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { UserInfoComponent } from '../user-info/user-info.component';

 
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  isLoggedin = false;
  errorMessage = '';
  info: any;
 
  constructor(private authService: AuthService, private token: TokenStorageService, private dialog: MatDialog) { }
 
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if (this.info.token) {
      this.isLoggedin = true;
    }
  }
 
  onSubmit() {
    console.log(this.form);
 
    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password);
 
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        alert("Registration was successful. You can now sign in!");
        this.reloadPage();
        
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    this.dialog.open(UserInfoComponent, {
      backdropClass : 'cdk-overlay-transparent-backdrop',
      position: {
        top: '50px',
        right: '20px'
      }
    });
  }

  reloadPage() {
    window.location.reload();
  }
}
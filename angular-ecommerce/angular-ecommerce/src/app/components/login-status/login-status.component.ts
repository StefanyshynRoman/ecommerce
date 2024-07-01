import { Component, Inject, OnInit } from '@angular/core';
import { OKTA_AUTH, OktaAuthStateService } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css'],
})
export class LoginStatusComponent implements OnInit {
  isAuthenticated = false;
  userFullName = '';
  constructor(
    private oktaAuthService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth,
  ) {}
  ngOnInit() {
    // subscribe to authentication
    this.oktaAuthService.authState$.subscribe((result) => {
      this.isAuthenticated = result.isAuthenticated!;
      this.getUserDetails();
    });
  }

  private getUserDetails() {
    if (this.isAuthenticated) {
      //fetch the logged-in user details(user's claims)
      //
      //user full name is exposed as a property name
      this.oktaAuth.getUser().then((res) => {
        this.userFullName = res.name as string;
      });
    }
  }
  logout() {
    //Terminates the session with Okta and removes current token
    this.oktaAuth.signOut();
  }
}

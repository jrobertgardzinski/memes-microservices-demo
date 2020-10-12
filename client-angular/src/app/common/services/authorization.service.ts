import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { JwtHelperService } from "@auth0/angular-jwt";
import { Observable } from 'rxjs';
import { tokenGetter } from 'src/app/app.module';
import { UserDetails } from 'src/app/common/model/user-details'

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  jwtHelperService: JwtHelperService = new JwtHelperService();
  userDetails: UserDetails;

  constructor(
    private httpClient: HttpClient
  ) { 
    if (tokenGetter()) {
      this.setUser();
    }
  }

  setUpAuthorizationData(jwt) {
    this.setJwt(jwt);
    this.setUser();
  }
  
  logIn(data): Observable<any> {
    const formData = new FormData();
    formData.append('username', data['username']);
    formData.append('password', data['password']);
    formData.append('grant_type', 'password');
    formData.append('scope', 'webclient');

    return this.httpClient.post(
      "http://localhost:5555/auth/oauth/token", 
      formData, 
      {
        headers: {
          "Authorization": "Basic " + btoa("eagleeye:thisissecret")
        }
      }
    );
  }

  private setJwt(jwt) {
    sessionStorage.setItem("access_token", jwt['access_token']);
    sessionStorage.setItem("expires_in", jwt['expires_in']);
    sessionStorage.setItem("refresh_token", jwt['refresh_token']);
    sessionStorage.setItem("token_type", jwt['token_type']);
    sessionStorage.setItem("scope", jwt['scope']);
  }

  private setUser() {
    this.httpClient.get(
      "http://localhost:5555/auth/user", 
      { 
        headers: { 
          "Authorization": "Bearer " + sessionStorage.getItem("access_token")
        }
      }).subscribe(
        data => this.userDetails = data as UserDetails
      );
  }

  public logOut() {
    this.userDetails = null;
    sessionStorage.removeItem("access_token");
    sessionStorage.removeItem("expires_in");
    sessionStorage.removeItem("refresh_token");
    sessionStorage.removeItem("token_type");
    sessionStorage.removeItem("scope");
  }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem("access_token") != null;
  }

  public getUserData() {
    return this.userDetails ?
      this.printUserDetailedInfo() :
      "Click to log in";
  }

  private printUserDetailedInfo(): string {
    return `USERNAME: ${this.userDetails.user.username}
            ROLES: \n - ${this.userDetails.user.authorities.map(element => element.authority).join('\n - ')}`;
  }
}

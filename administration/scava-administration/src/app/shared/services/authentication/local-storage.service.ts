import { Injectable } from '@angular/core';
import { JwtHelper } from 'angular2-jwt';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  private jwtHelper: JwtHelper;
  
  constructor() {
    this.jwtHelper = new JwtHelper();
   }

  saveLoginStatus(status: string) {
    localStorage.setItem('isLoggedin', status);
  }
  
  loadLoginStatus() {
    return localStorage.getItem('isLoggedin');
  }

  saveToken(token: string) {
    localStorage.setItem('jwtToken', token);
  }

  loadToken() {
    return localStorage.getItem('jwtToken');
  }

  getUserRoles() {
    let jwtToken: string = this.loadToken();
    if(jwtToken != null) {
      let roles: any = this.jwtHelper.decodeToken(jwtToken);
      return roles.authorities;
    }
  }

  hasRole(role: string) {
    let jwtToken: string = this.loadToken();
    if (jwtToken != null) {
      let roles = this.jwtHelper.decodeToken(jwtToken);
      for (let r of roles.authorities) {
        if (r == role)
          return true;
      }
    }
    return false;
  }

  getUsername() {
    let jwtToken: string = this.loadToken();
    if (jwtToken !== null) {
      return this.jwtHelper.decodeToken(jwtToken).sub;
    }
    return;
  }

}

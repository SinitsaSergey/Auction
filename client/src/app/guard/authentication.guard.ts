import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }

    this.router.navigateByUrl('/login');
    return false;
  }

}

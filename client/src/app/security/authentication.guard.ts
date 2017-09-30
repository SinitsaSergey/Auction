import {Inject, Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {AuthenticationService} from "../service/authentication.service";
import {User} from "../model/user";
import {Authority} from "../model/authority";

@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router,
              @Inject ('authenticationService') private authenticationService: AuthenticationService) {
  }

  canActivate(route: ActivatedRouteSnapshot): Promise<boolean>|boolean {
    if (!this.authenticationService.currentUser) return false;
    const myRole = this.authenticationService.currentUser.authorities[0].authority;
    console.log(myRole);
    const roles = route.data['roles'] as Array<string>;
    console.log(roles);
    if (!roles || roles.indexOf(myRole) !== -1) return true;
    else {
      this.router.navigate(['/admin']);
      return false;
    }
  }




  /*canActivate(route: ActivatedRouteSnapshot) {
    const roles = route.data['roles'] as Array<string>;
    const user: User = this.authenticationService.currentUser;
    if (roles.length > 0 && user) {
      if (this.checkAuthorities(roles, user.authorities))
        return true;
    }
    alert('У вас недостаточно прав для перехода на эту страницу!');
    this.router.navigateByUrl('/home');
    return false;
  }

  private static checkAuthorities(avialableAuthorityList: string[], currentAuthorityList: Authority[]): boolean {
    for (let avialableAuthority = 0; avialableAuthority < avialableAuthorityList.length; avialableAuthority++) {
      for (let userAuthority = 0; userAuthority < currentAuthorityList.length; userAuthority++)
        if (avialableAuthorityList[avialableAuthority] == currentAuthorityList[userAuthority].authority) {
          return true;
        }
    }
    return false;
  }

  public static hasAuthority(authority: string): boolean {
    const user: User = AuthenticationService.currentUser;
    if (user != null)
      for (let currentAuthority of user.authorities) {
        if (currentAuthority.authority === authority)
          return true;
      }
    return false;
  }*/

}

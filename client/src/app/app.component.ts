import {Component, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {AuthenticationService} from "./service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  welcomeName: string;
  title = 'Аукцион';

  constructor(private router: Router,
              private translate: TranslateService,
             @Inject ('authenticationService') private authenticationService: AuthenticationService) {
    translate.setDefaultLang('en');
  }

  public isLoggedIn(): boolean {
    this.initUser();
    if (this.authenticationService.currentUser) {
      this.welcomeName = this.authenticationService.currentUser.firstName;
      return true;
    }
    return false;
  }

  public isAdmin(): boolean {
    if (!this.authenticationService.currentUser) return false;
    if (this.authenticationService.currentUser.authorities[0].authority === 'ROLE_ADMIN') return true;
    return false;
  }

  public isManager(): boolean {
    if (!this.authenticationService.currentUser) return false;
    if (this.authenticationService.currentUser.authorities[0].authority === 'ROLE_MANAGER') return true;
    return false;
  }

  public isUser(): boolean {
    if (!this.authenticationService.currentUser) return false;
    if (this.authenticationService.currentUser.authorities[0].authority === 'ROLE_USER') return true;
    return false;
  }

  public logout(): void {
    this.authenticationService.logout();
  }

  initUser(): void {
    if (this.authenticationService.username === 'guest') {
      return;
    }
    if (!this.authenticationService.currentUser) this.authenticationService.getCurrentUser();
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }

  goHome(): void {
    this.router.navigateByUrl('');
  }

}

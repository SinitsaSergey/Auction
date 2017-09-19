import {ChangeDetectorRef, Component, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {AuthenticationService} from "./service/authentication.service";
import {User} from "./model/user";

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
             private authenticationService: AuthenticationService
              ) {
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

import { Component } from '@angular/core';
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
              private authentication: AuthenticationService) {
    translate.setDefaultLang('en');
  }

  public isLoggedIn(): boolean {
    if (this.authentication.username) {
      this.welcomeName = this.authentication.username;
      return true;
    }
    return false;
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }

  goHome(): void {
    this.router.navigateByUrl('');
  }

}

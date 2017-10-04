import {Component, Inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  model: any = {};
  loading = false;
  errorMessage: string;

  constructor(private router: Router,
              @Inject ('authenticationService') private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.authenticationService.logout();
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(result => {
        if (result === true) {
          this.router.navigateByUrl('/');
        } else {
          this.errorMessage = 'Имя пользователя или пароль введены неверно';
         this.loading = false;
        }
      });
  }
}

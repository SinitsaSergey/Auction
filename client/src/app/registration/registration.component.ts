import {Component, Inject, Input, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {AlertService} from '../service/alert.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User;
  loading = false;
  confirmPassword: string;
  failedMessage: string;
  successMessage: string;

  constructor(@Inject('userService') private userService: UserService,
              private router: Router) {
    this.user = new User;
  }

  ngOnInit() {
  }

  insert(): void {
    this.loading = true;
    if (this.user.password !== this.confirmPassword) {
      this.failedMessage = 'Введенные пароли не совпадают';
      return;
    }
    this.userService.insert(this.user)
      .then(user => {
        this.failedMessage = null;
        this.successMessage = 'Регистрация прошла успешно. Можете войти на сайт!';
      })
      .catch(error => {
        this.loading = false;
        this.failedMessage = 'Проверьте правильность введенных данных!';
      });
  }

}

import {Component, Inject, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {User} from "../model/user";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  currentUser: User;
  loading = false;
  changing = false;
  failedMessage = '';
  successMessage = '';
  newPassword: string;
  confirmPassword: string;

  constructor(@Inject ('authenticationService') private authenticationService: AuthenticationService,
              @Inject('userService') private userService: UserService) {
  }

  ngOnInit() {
  }

  insert(): void {
    this.userService.update(this.currentUser)
      .then(() => {
      this.currentUser = null;
        this.failedMessage = null;
        this.successMessage = 'Данные усешно изменены!';
      })
      .catch(error => {
        this.loading = false;
        this.failedMessage = 'Проверьте правильность введенных данных!';
      });
  }

  changePassword(): void {
    if (this.newPassword !== this.confirmPassword){
      this.failedMessage = 'Пароли не совпадают';
      return;
    }
    this.userService.changePassword(this.newPassword)
      .then(() =>{
      this.changing = false;
      this.successMessage = 'Пароль успешно изменен!';
      })
      .catch(() => {
        this.changing = false;
        this.failedMessage = 'Не удалось изменить пароль!';
      });
  }

  getCurrentUser(): void {
    this.changing = false;
    this.currentUser = this.authenticationService.currentUser;
    this.clearMessages();
  }

  changeOn() {
    this.newPassword = '';
    this.confirmPassword = '';
    this.changing = true;
    this.currentUser = null;
    this.clearMessages();
  }

  clearMessages() {
    this.successMessage = null;
    this.failedMessage = null;
  }
}

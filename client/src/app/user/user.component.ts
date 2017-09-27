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
  confirmPassword: string;

  constructor(@Inject ('authenticationService') private authenticationService: AuthenticationService,
              @Inject('userService') private userService: UserService) {
  }

  ngOnInit() {
  }

  insert(): void {
    this.userService.update(this.currentUser)
      .then(user => this.currentUser = user);
  }

  getCurrentUser(): void {
    this.currentUser = this.authenticationService.currentUser;
  }

}

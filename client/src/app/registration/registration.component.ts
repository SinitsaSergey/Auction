import {Component, Inject, Input, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User;
  confirmPassword: string;

  constructor(@Inject('userService') private userService: UserService) {
    this.user = new User;
  }

  ngOnInit() {
  }

  insert(): void {
    console.log ('comp +' + this.user);
    this.userService.insert(this.user)
      .then(user => this.user = user);
  }

}

import {Component, Inject, Input, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Input()
  user: User;

  constructor(@Inject('userService') private userService: UserService) {
    this.user = new User;
  }

  ngOnInit() {
  }

  insert(user): void {
    this.userService.insert(user)
      .then(user => this.user = user);
  }

}

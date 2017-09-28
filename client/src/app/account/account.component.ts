import {Component, Inject, OnInit} from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {User} from "../model/user";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {


  constructor() {
  }

  ngOnInit() {
  }

}

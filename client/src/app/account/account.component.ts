import {Component, Inject, OnInit} from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  welcomeName: string;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

}

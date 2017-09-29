import {Component, Inject, OnInit} from '@angular/core';
import {Lot} from "../model/lot";
import {Auction} from "../model/auction";
import {TradingDay} from "../model/trading-day";
import {ManagerService} from "../service/manager.service";
import {AuthenticationService} from "../service/authentication.service";
import {User} from "../model/user";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  tradingDays: TradingDay[];
  manager: User;

  constructor(@Inject ('managerService') private managerService: ManagerService,
              @Inject ('authenticationService') private authenticationService: AuthenticationService) {
    this.manager = authenticationService.currentUser;
  }

  ngOnInit() {
    this.getMyDays();
  }

  getMyDays(): void {
this.managerService.getMyDays()
  .then(tradingDays => this.tradingDays = tradingDays);
  }

}

import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../service/user.service";
import {AdminService} from "../service/admin.service";
import {TradingDay} from "../model/trading-day";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[];
  selectedUsername: string;
  selectedUser: User;
  stringDate: string;
  tradingDay: TradingDay;
  loading = false;

  constructor(@Inject('userService') private userService: UserService,
              @Inject('adminService') private adminService: AdminService) {
  }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.userService.getAllUsers()
      .then(users => this.users = users);
  }

  setAs(authority: string): void {
    this.userService.setAs(authority, this.selectedUser)
      .then(user => this.selectedUser = user);
  }

  selectUser(): void {
    this.userService.getByUsername(this.selectedUsername)
      .then(user => this.selectedUser = user);
  }

  banUser(isBanned: boolean): void {
    this.userService.setBan(isBanned, this.selectedUser)
      .then(user => this.selectedUser = user);
  }

  isManager(): boolean {
    if (this.selectedUser.authorities[0].authority === 'ROLE_MANAGER') return true;
    return false;
  }

  isUser(): boolean {
    if (this.selectedUser.authorities[0].authority === 'ROLE_USER') return true;
    return false;
  }

  createTradingDay(): void {
    this.adminService.insertTradingDay(this.stringDate, this.selectedUser)
      .then(tradingDay => this.tradingDay = tradingDay);
  }

  getTradingDay() {
    this.loading = true;
    this.tradingDay = null;
    this.adminService.getTradingDay(this.stringDate)
      .then(tradingDay => this.tradingDay = tradingDay);
    this.loading = false;
  }
}

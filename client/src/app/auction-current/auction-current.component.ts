import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {ActivatedRoute} from "@angular/router";
import {DateUtils} from "../utils/date-utils";
import {AuthenticationService} from "../service/authentication.service";
import {User} from "../model/user";

const DURATION = 600000;

@Component({
  selector: 'app-auction-current',
  templateUrl: './auction-current.component.html',
  styleUrls: ['./auction-current.component.css']
})
export class AuctionCurrentComponent implements OnInit, OnDestroy {

  private sub: any;
  id: number;
  currentAuction: Auction;
  currentPrice: number;
  currentStatus: string;
  refreshInterval: any;
  enableTrading = true;
  currentUser: User;

  constructor(private router: ActivatedRoute,
              @Inject('auctionService') private auctionService: AuctionService,
              @Inject('authenticationService') private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.id = params['id'];
      this.auctionService.getById(this.id)
        .then(auction => {
          this.currentAuction = auction;
          this.currentStatus = 'onsale';
        });
    });
    this.currentUser = this.authenticationService.currentUser;
    this.refreshInterval = setInterval(() => {
      this.getCurrentPrice();
      this.getFinishTime();
    }, 1000);
  }

  ngOnDestroy() {
    clearInterval(this.refreshInterval);
  }

  getRestTime(): number {
    const restTime = (+this.currentAuction.finishTime - Date.now()) / 1000;
    if (restTime <= 0) {
      this.enableTrading = false;
      return 0;
    }
    return restTime;
  }

  getCurrentPrice(): void {
    this.auctionService.getCurrentPrice(this.currentAuction.id)
      .then(price => this.currentPrice = price);
  }

  getFinishTime(): void {
    this.auctionService.getFinishTime(this.currentAuction.id)
      .then(time => this.currentAuction.finishTime = new Date(time));
  }

  placeBid(): void {
    this.auctionService.placeBid(this.currentAuction.id)
      .then(price => this.currentPrice = price);
  }

  timeToString(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time - minutes * 60);
    return (minutes > 9 ? '' + minutes : '0' + minutes) + ':' + (seconds > 9 ? seconds : '0' + seconds);
  }

  getTimeLimit() {
    return new Date (+this.currentAuction.startTime + DURATION);
  }

  getBid(currentPrice): string {
    return (+currentPrice).toFixed(2);
  }

  anotherUser() {
    return this.currentUser.username !== this.currentAuction.lot.seller.username;
  }

  isBidholder() {
    return this.currentUser.username === this.currentAuction.bidholder.username;
  }
}

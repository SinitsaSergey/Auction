import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {ActivatedRoute} from "@angular/router";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-auction-current',
  templateUrl: './auction-current.component.html',
  styleUrls: ['./auction-current.component.css']
})
export class AuctionCurrentComponent implements OnInit, OnDestroy {

  private sub: any;
  id: number;
  currentAuction: Auction;
  getNow = DateUtils.getNow;
  currentPrice: number;
  currentStatus: string;
  refreshInterval: any;
  enableTrading = true;
  timer: number;

  constructor(private router: ActivatedRoute,
              @Inject('auctionService') private auctionService: AuctionService) {
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
    this.refreshInterval = setInterval(() => {
      this.getCurrentPrice();
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
      .then(time => this.currentAuction.finishTime = new Date (time));
}

  placeBid(): void {
    this.auctionService.placeBid(this.currentAuction.id)
      .then(price => this.currentPrice = price);
    this.getFinishTime();
  }

  timeToString(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time - minutes * 60);
    return (minutes > 9 ? '' + minutes : '0' + minutes) + ':' + (seconds > 9 ? seconds : '0' + seconds);
  }
}

import {Component, Inject, OnInit} from '@angular/core';
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {ActivatedRoute} from "@angular/router";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-auction-current',
  templateUrl: './auction-current.component.html',
  styleUrls: ['./auction-current.component.css']
})
export class AuctionCurrentComponent implements OnInit {

  private sub: any;
  id: number;
  currentAuction: Auction;
  restTime: number;
  currentPrice: number;
  refreshInterval: any;

  constructor(private router: ActivatedRoute,
              @Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.id = params['id'];
      this.auctionService.getById(this.id)
        .then(auction => {
          this.currentAuction = auction;
          this.currentPrice = auction.lot.startPrice;
        });
    });
    this.refreshInterval = setInterval(() => this.getRestTime(), 1000);
  }

getRestTime(): number {
   return this.restTime = (this.getFinishTime().getTime() - new Date().getTime()) / 1000;
}

getFinishTime(): Date {
    return new Date(+this.currentAuction.startTime + 600000);
}

getDate (date): Date {
    return new Date (date);
}

  timeToString(time: number): string {
    const minutes = (time / 60).toFixed(0);
    const seconds = (time % 60).toFixed(0);
   return (+minutes > 9 ? minutes : '0' + minutes) + ':' + (+seconds > 9 ? seconds : '0' + seconds);
  }

}

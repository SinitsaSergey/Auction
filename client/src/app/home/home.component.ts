import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {DateUtils} from "../utils/date-utils";
import {TimeInterval} from "rxjs/Rx";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  currentAuction: Auction;
  auctions: Auction[];
  tradingDate: Date;
  convertStartTime = DateUtils.convertStartTime;
  getFinishTime = DateUtils.getFinishTime;
  convertToModel = DateUtils.convertDateToModel;
  refreshInterval: any;

  constructor(@Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.tradingDate = new Date();
    this.getAuctionsForDay();
    this.refreshInterval = setInterval(() => {this.getCurrentAuction();}, 1000);
  }

  ngOnDestroy() {
    clearInterval(this.refreshInterval);
  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.convertToModel(this.tradingDate))
      .then(auctions => this.auctions = auctions);
  }

  getDate() {
    return new Date();
  }

  isActual(auction: Auction): boolean {
    return auction.startTime > this.getDate() && auction.lot.status.status === 'onsale';
  }

  getCurrentAuction(): void {
    for (const auction of this.auctions){
      if (auction.startTime < this.getDate() && auction.lot.status.status === 'onsale'){
        this.currentAuction = auction;
      }
    }
  }

}

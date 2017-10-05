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
  convertToModel = DateUtils.convertDateToModel;
  getNow = DateUtils.getNow;
  refreshInterval: any;
  noAuctionsMessage = 'На сегодня нет доступных аукционов';

  constructor(@Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.getAuctionsForDay();
   this.refreshInterval = setInterval(() => {
      this.getAuctionsForDay();
      this.getCurrentAuction();
    }, 1000);
  }

  ngOnDestroy() {
    clearInterval(this.refreshInterval);
  }

  getAuctionsForDay(): void {
    this.auctionService.getOnSaleForDay(this.convertToModel(new Date()))
      .then(auctions => {
        this.auctions = auctions;
      });
  }

  isActual(auction: Auction): boolean {
    return +auction.startTime > this.getNow()
      && auction.lot.status.status === 'onsale';
  }

  getCurrentAuction(): void {
    this.currentAuction = null;
    if (!this.auctions) return;
    for (const auction of this.auctions) {
      if (+auction.startTime < this.getNow()
        && +auction.finishTime > this.getNow()) {
        this.currentAuction = auction;
      }
    }
  }

  auctionsExist(): boolean {
   if (!this.auctions) return false;
    if (this.auctions.length > 0) return true;
    return false;
  }

}

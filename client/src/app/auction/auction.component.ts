import { Component, OnInit } from '@angular/core';
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.css']
})
export class AuctionComponent implements OnInit {

  auctions: Auction[];
  tradingDate: Date;

  constructor(private auctionService: AuctionService) { }

  ngOnInit() {
    this.tradingDate = new Date();
    this.getAuctionsForDay();
  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.tradingDate)
      .then(auctions => this.auctions = auctions);
  }

  convertStartTime (auction: Auction): string {
    return new Date(auction.startTime).toLocaleTimeString();
  }

  getFinishTime(auction: Auction): string {
    return new Date (auction.startTime + auction.duration).toLocaleTimeString();
  }

}

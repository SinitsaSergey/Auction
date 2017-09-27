import {Component, Inject, OnInit} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  auctions: Auction[];
  tradingDate: Date;
  convertStartTime = DateUtils.convertStartTime;
  getFinishTime = DateUtils.getFinishTime;
  convertToModel = DateUtils.convertDateToModel;

  constructor(@Inject ('auctionService') private auctionService: AuctionService) { }

  ngOnInit() {
    this.tradingDate = new Date();
    this.getAuctionsForDay();
  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.convertToModel(this.tradingDate))
      .then(auctions => this.auctions = auctions);
  }
}

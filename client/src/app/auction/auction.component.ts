import {Component, OnInit} from '@angular/core';
import {Auction} from "../model/auction";
import {AuctionService} from "../service/auction.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TradingDay} from "../model/trading-day";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.css']
})
export class AuctionComponent implements OnInit {

  private sub: any;
  date: string;
  auctions: Auction[];
  convertStartTime = DateUtils.convertStartTime;
  getFinishTime = DateUtils.getFinishTime;

  constructor(private router: ActivatedRoute,
              private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.date = params['date'];
      this.auctionService.getAllForDay(this.date)
        .then(auctions => this.auctions = auctions);
    });
  }

}

import {Component, OnInit} from '@angular/core';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TradingDay} from '../model/trading-day';
import {DateUtils} from '../utils/date-utils';
import {Lot} from '../model/lot';
import {LotService} from '../service/lot.service';
import {isQuote} from "@angular/compiler";
import {AdminService} from "../service/admin.service";

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.css']
})
export class AuctionComponent implements OnInit {

  private sub: any;
  date: string;
  auctions: Auction[];
  startTimeString: string;
  convertStartTime = DateUtils.convertStartTime;
  getFinishTime = DateUtils.getFinishTime;
  newAuction: Auction;
  newDuration: number;
  selectedLot: Lot;
  regLots: Lot[];
  freeLots: Lot[];

  constructor(private router: ActivatedRoute,
              private auctionService: AuctionService,
              private lotService: LotService,
              private adminService: AdminService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.date = params['date'];
      this.getAuctionsForDay();
      this.getFreeLots();
    });
  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.date)
      .then(auctions => this.auctions = auctions);
  }

  dayIsFull(): boolean {
    return AuctionService.dayIsFull(this.auctions);
  }

  getFreeLots(): void {
    this.lotService.getByStatus('registered')
      .then(lots => this.regLots = lots);
    this.lotService.getByStatus('free')
      .then(lots => this.freeLots = lots);
  }

  getLots(): Lot[] {
    if (this.dayIsFull()) {
      return this.regLots;
    }else {
      return this.freeLots;
    }
  }

  getDate (date: Date) {
    return new Date(date);
  }

  getTradingDay(): void {
    this.adminService.getTradingDay(this.date)
      .then(tradingDay => this.newAuction.tradingDay = tradingDay);
  }

  public convertToDate (): void {
    this.newAuction.startTime = new Date();
    const dateArray = this.date.split('-', 3);
    const timeArray = this.startTimeString.split(':', 2);
    this.newAuction.startTime.setFullYear(+dateArray[0], +dateArray[1] - 1, +dateArray[2]);
    this.newAuction.startTime.setHours(+timeArray[0], +timeArray[1], 0, 1);
  }

  insert (): void {
    this.newAuction.lot = this.selectedLot;
    this.getTradingDay();
    this.auctionService.insert(this.newAuction, this.dayIsFull())
      .then(() => this.getAuctionsForDay());
  }

}

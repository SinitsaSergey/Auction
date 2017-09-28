import {Component, Inject, OnInit} from '@angular/core';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TradingDay} from '../model/trading-day';
import {DateUtils} from '../utils/date-utils';
import {Lot} from '../model/lot';
import {LotService} from '../service/lot.service';
import {isQuote} from "@angular/compiler";
import {AdminService} from "../service/admin.service";

const MAX_AUCTIONS = 2;
const DURATION = 60000;

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.css']
})
export class AuctionComponent implements OnInit {

  private sub: any;
  date: string;
  auctions: Auction[] = [];
  queueAuctions: Auction[] = [];
  startTimeString: string;
  convertStartTime = DateUtils.convertStartTime;
  getFinishTime = DateUtils.getFinishTime;
  newAuction: Auction;
  selectedLot: Lot;
  tradingDay: TradingDay;
  regLots: Lot[];
  freeLots: Lot[];

  constructor(private router: ActivatedRoute,
              @Inject ('auctionService') private auctionService: AuctionService,
              @Inject ('lotService') private lotService: LotService,
              @Inject ('adminService') private adminService: AdminService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.date = params['date'];
      this.getAuctionsForDay();
      this.getFreeLots();
      this.getTradingDay();
    });
  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.date)
      .then(auctions => this.auctions = auctions);
  }

  /*dayIsFull(): boolean {
    return AuctionService.dayIsFull(this.auctions);
  }*/

  dayIsFull (): boolean {
    return (this.auctions.length >= MAX_AUCTIONS);
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
      .then(tradingDay => this.tradingDay = tradingDay);
  }

  public convertToDate (): void {
    this.newAuction.startTime = new Date();
    const dateArray = this.date.split('-', 3);
    const timeArray = this.startTimeString.split(':', 2);
    this.newAuction.startTime.setFullYear(+dateArray[0], +dateArray[1] - 1, +dateArray[2]);
    this.newAuction.startTime.setHours(+timeArray[0], +timeArray[1], 0, 0);
  }

  insert (): void {
    this.newAuction.lot = this.selectedLot;
    this.newAuction.tradingDay = this.tradingDay;
    this.auctionService.insert(this.newAuction, this.dayIsFull())
      .then(() => this.getAuctionsForDay());
  }

  remove (id): void {
    this.auctionService.remove(id)
      .then(() => this.getAuctionsForDay());

  }


}

import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TradingDay} from '../model/trading-day';
import {Lot} from '../model/lot';
import {LotService} from '../service/lot.service';
import {AdminService} from "../service/admin.service";
import {parseHttpResponse} from "selenium-webdriver/http";

const MAX_AUCTIONS = 2;
const DURATION = 600000;

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.css']
})
export class AuctionComponent implements OnInit, OnDestroy {

  private sub: any;
  date: string;
  loading = false;
  auctions: Auction[] = [];
  queueAuctions: Auction[] = [];
  startTimeString: string;
  newAuction: Auction;
  selectedLot: Lot;
  tradingDay: TradingDay;
  regLots: Lot[];
  freeLots: Lot[];

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              @Inject('auctionService') private auctionService: AuctionService,
              @Inject('lotService') private lotService: LotService,
              @Inject('adminService') private adminService: AdminService) {
  }

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.date = params['date'];
      this.getAuctionsForDay();
      this.getFreeLots();
      this.getTradingDay();
    });
  }

  ngOnDestroy() {

  }

  getAuctionsForDay(): void {
    this.auctionService.getAllForDay(this.date)
      .then(auctions => this.auctions = auctions);
  }

  dayIsFull(): boolean {
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
    } else {
      return this.freeLots;
    }
  }

  getTradingDay(): void {
    this.adminService.getTradingDay(this.date)
      .then(tradingDay => this.tradingDay = tradingDay);
  }

  public convertToDate(): void {
    this.newAuction.startTime = new Date();
    const dateArray = this.date.split('-', 3);
    const timeArray = this.startTimeString.split(':', 2);
    this.newAuction.startTime.setFullYear(+dateArray[0], +dateArray[1] - 1, +dateArray[2]);
    this.newAuction.startTime.setHours(+timeArray[0], +timeArray[1], 0, 0);
  }

  insert(): void {
    this.newAuction.lot = this.selectedLot;
    this.newAuction.tradingDay = this.tradingDay;
    this.newAuction.finishTime = new Date(+this.newAuction.startTime + DURATION);
    this.newAuction.currentBid = this.newAuction.lot.startPrice;
    this.newAuction.bidTime = new Date();
    this.auctionService.insert(this.newAuction, this.dayIsFull())
      .then(() => {
        this.getNewAuction();
      });
  }

  remove(id): void {
    this.auctionService.remove(id)
      .then(success => {
        this.getAuctionsForDay();
        this.getFreeLots();
      });

  }

  confirm(lot: Lot): void {
    this.lotService.confirm(lot)
      .then(success => {
        this.getAuctionsForDay();
        this.getFreeLots();
      });
  }

  getNewAuction() {
    this.newAuction = new Auction();
    this.selectedLot = null;
    this.startTimeString = null;
    this.getAuctionsForDay();
    this.getFreeLots();
  }
}

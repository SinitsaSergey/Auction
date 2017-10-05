import {Component, Inject, OnInit} from '@angular/core';
import {Lot} from '../model/lot';
import {LotService} from "../service/lot.service";
import {AuctionService} from "../service/auction.service";
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-lot',
  templateUrl: './lot.component.html',
  styleUrls: ['./lot.component.css']
})
export class LotComponent implements OnInit {

  lot: Lot;
  myLots: Lot[];
  purchasedLots: Lot[];
  purchasedLotBid: string;

  constructor(@Inject('lotService') private lotService: LotService,
              @Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.getMyLots();
    this.getPurchasedLots();
  }

  insert(): void {
    this.lotService.insert(this.lot)
      .then(() => {
        this.lot = null;
        this.getMyLots();
      });
  }

  getMyLots(): void {
    this.lot = null;
    this.lotService.getMyLots()
      .then(lots => this.myLots = lots);
  }

  getPurchasedLots(): void {
    this.lotService.getPurchasedLots()
      .then(lots => this.purchasedLots = lots);
  }

  createLot() {
    this.myLots = null;
    this.lot = new Lot;
  }

  remove(id): void {
    this.lotService.remove(id)
      .then(success => this.getMyLots());
  }

  getFinishPrice(lot) {
    this.auctionService.getByLot(lot.id)
      .then(auction => this.purchasedLotBid = (+auction.currentBid).toFixed(2));
    return this.purchasedLotBid;
  }
}

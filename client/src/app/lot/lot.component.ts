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
  showLots: boolean;

  constructor(@Inject('lotService') private lotService: LotService,
              @Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
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

  createLot() {
    this.myLots = null;
    this.lot = new Lot;
  }

  remove(id): void {
    this.lotService.remove(id);
    this.getMyLots();
  }
}

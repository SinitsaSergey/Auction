import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {DateUtils} from '../utils/date-utils';
import {Lot} from "../model/lot";

const DURATION = 600000;

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.css']
})
export class AuctionDetailsComponent implements OnInit {

  private sub: any;
  id: number;
  auction: Auction;
  newStartTimeString: string;

  constructor(private router: ActivatedRoute,
              @Inject('auctionService') private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.id = params['id'];
      this.auctionService.getById(this.id)
        .then(auction => this.auction = auction);
    });
  }

  insert(): void {
    this.convertToDate();
    const isQueue = (this.auction.lot.status.status === 'queue');
    this.auctionService.insert(this.auction, isQueue)
      .then(auction => this.auction);
  }

  public convertToDate(): void {
    this.auction.startTime = new Date(Date.parse(this.newStartTimeString));
   const timeArray = this.newStartTimeString.split(':', 2);
    this.auction.startTime.setUTCHours(+timeArray[0], +timeArray[1], 0, 0);
    this.auction.finishTime = new Date (this.auction.startTime.getTime() + DURATION);
  }

}

import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {DateUtils} from '../utils/date-utils';
import {Lot} from "../model/lot";

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.css']
})
export class AuctionDetailsComponent implements OnInit {

  private sub: any;
  id: number;
  auction: Auction;
  getDate = DateUtils.getDate;
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

  timeToString(date: Date): string {
    const time = new Date(date);
    const hours = time.getHours();
    const minutes = time.getMinutes();
    return (hours > 9 ? hours : '0' + hours) + ':' + (minutes > 9 ? minutes : '0' + minutes);
  }

  insert(): void {
    this.convertToDate();
    const isQueue = (this.auction.lot.status.status === 'queue');
    this.auctionService.insert(this.auction, isQueue)
      .then(auction => this.auction);
  }

  public convertToDate(): void {
    this.auction.startTime = new Date(this.auction.startTime);
    const timeArray = this.newStartTimeString.split(':', 2);
    this.auction.startTime.setHours(+timeArray[0], +timeArray[1], 0, 0);
  }

}

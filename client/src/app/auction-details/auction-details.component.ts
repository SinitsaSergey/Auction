import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Auction} from '../model/auction';
import {AuctionService} from '../service/auction.service';
import {DateUtils} from '../utils/date-utils';

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

  constructor(private router: ActivatedRoute,
              private auctionService: AuctionService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.id = params['id'];
      this.auctionService.getById(this.id)
        .then(auction => this.auction = auction);
    });
  }

  timeToString (date: Date): string {
    const time = new Date(date);
    const hours = time.getHours();
    const minutes = time.getMinutes();
    return (hours > 9 ? hours : '0' + hours) + ':' + (minutes > 9 ? minutes : '0' + minutes);
  }

  convertDuration (duration: number): string {
    const minutes = Math.floor(duration / 60000);
    const seconds = ((duration % 60000) / 1000).toFixed(0);
    return minutes + ':' + (+seconds < 10 ? '0' : '') + seconds;
  }

}

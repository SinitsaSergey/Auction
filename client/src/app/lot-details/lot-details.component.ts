import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AuctionService} from "../service/auction.service";
import {Lot} from "../model/lot";
import {LotService} from "../service/lot.service";

@Component({
  selector: 'app-lot-details',
  templateUrl: './lot-details.component.html',
  styleUrls: ['./lot-details.component.css']
})
export class LotDetailsComponent implements OnInit {

  private sub: any;
  id: number;
  lot: Lot;

  constructor(private router: ActivatedRoute,
              @Inject('lotService') private lotService: LotService) {
  }

  ngOnInit() {
    this.sub = this.router.params.subscribe(params => {
      this.id = params['id'];
      this.lotService.getById(this.id)
        .then(lot => this.lot = lot);
    });
  }

}

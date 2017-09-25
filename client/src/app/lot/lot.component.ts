import {Component, Inject, OnInit} from '@angular/core';
import {Lot} from '../model/lot';
import {LotService} from "../service/lot.service";

@Component({
  selector: 'app-lot',
  templateUrl: './lot.component.html',
  styleUrls: ['./lot.component.css']
})
export class LotComponent implements OnInit {

  lot: Lot;

  constructor(@Inject('lotService') private lotService: LotService ) {
  }

  ngOnInit() {
  }

  insert(lot): void {
    this.lotService.insert(lot)
      .then(lot => this.lot = lot);
  }

}

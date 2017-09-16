import {Component, Inject, Input, OnInit} from '@angular/core';
import {Lot} from '../model/lot';
import LotService from '../interface/lot.service';
import {FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-lot',
  templateUrl: './lot.component.html',
  styleUrls: ['./lot.component.css']
})
export class LotComponent implements OnInit {

  @Input()
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

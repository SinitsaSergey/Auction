import {Component, OnDestroy, OnInit} from '@angular/core';
import {DateUtils} from "../utils/date-utils";

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit, OnDestroy {

  getNow = DateUtils.getNow;
  timerInterval: any;

  constructor() { }

  ngOnInit() {
   this.timerInterval = setInterval(() => void(0) , 1000);
  }

  ngOnDestroy(): void {
    clearInterval(this.timerInterval);
  }


}

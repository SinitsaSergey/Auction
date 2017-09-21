import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {TradingDay} from "../model/trading-day";
import {User} from "../model/user";

const TRADING_DAY_PATH = 'api/trading-day/';

@Injectable()
export class AdminService {

  constructor(private http: Http) {
  }

  insertTradingDay(date: Date, manager: User): Promise<TradingDay> {
    return this.http.post(TRADING_DAY_PATH + date, manager)
      .toPromise()
      .then(response => response.json());
  }

  getTradingDay (date: Date): Promise<TradingDay> {
    return this.http.get(TRADING_DAY_PATH + date)
      .toPromise()
      .then(response => response.json());
  }

}

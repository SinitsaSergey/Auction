import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {TradingDay} from "../model/trading-day";
import {User} from "../model/user";
import {HttpClient} from "@angular/common/http";

const TRADING_DAY_PATH = 'api/trading-day/';

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) {
  }

  insertTradingDay(date: string, manager: User): Promise<TradingDay> {
    return this.http.post<TradingDay>(TRADING_DAY_PATH + date, manager)
      .toPromise();
  }

  getTradingDay (date: string): Promise<TradingDay> {
    return this.http.get<TradingDay>(TRADING_DAY_PATH + date)
      .toPromise();
  }

}

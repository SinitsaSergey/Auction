import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {TradingDay} from "../model/trading-day";
import {HttpClient} from "@angular/common/http";

const TRADING_DAY_PATH = 'api/trading-day/';

@Injectable()
export class ManagerService {

  constructor(private http: HttpClient) {
  }

  getMyDays (): Promise<TradingDay[]> {
    return this.http.get<TradingDay[]>(TRADING_DAY_PATH + 'my')
      .toPromise();
  }

}

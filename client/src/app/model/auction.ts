import {TradingDay} from './trading-day';
import {Lot} from './lot';
import {Bid} from "./bid";
import {User} from "./user";

export class Auction {
  id: number;
  tradingDay: TradingDay;
  startTime: Date;
  finishTime: Date;
  stepPrice: number;
  currentBid: number;
  bidholder: User;
  bidTime: Date;
  bidList: Bid[];
  lot: Lot;
}

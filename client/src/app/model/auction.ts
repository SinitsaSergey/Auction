import {TradingDay} from './trading-day';
import {Lot} from './lot';
import {Bid} from "./bid";

export class Auction {
  id: number;
  tradingDay: TradingDay;
  startTime: Date;
  finishTime: Date;
  stepPrice: number;
  currentBid: number;
  bidTime: Date;
  bidList: Bid[];
  lot: Lot;
}

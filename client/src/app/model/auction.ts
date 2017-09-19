import {TradingDay} from './trading-day';
import {Lot} from './lot';
import {Bid} from "./bid";

export class Auction{
  id: number;
  tradingDay: TradingDay;
  startTime: number;
  duration: number;
  stepPrice: number;
  bidList: Bid[];
  lot: Lot;
}

import {Auction} from "../model/auction";
import {TradingDay} from "../model/trading-day";

const AUCTION_DURATION = 600000;

export class DateUtils {

  public static getFinishTime(auction: Auction): Date {
    return new Date (+auction.startTime + AUCTION_DURATION);
  }

  public static convertStartTime (auction: Auction): string {
    return new Date(auction.startTime).toLocaleTimeString();
  }

  public static getDate(day: TradingDay): Date {
    return new Date(day.tradingDate);
  }

  public static convertDateToModel (date: Date): string {
    return date.getUTCFullYear() +
      '-' + (date.getUTCMonth() + 1) +
      '-' + (date.getUTCDate());
  }

}

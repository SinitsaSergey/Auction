import {Auction} from "../model/auction";
import {TradingDay} from "../model/trading-day";

export class DateUtils {

 /* public static getFinishTime(auction: Auction): Date {
    return new Date (+auction.startTime + AUCTION_DURATION);
  }*/

  /*public static getStartTime (auction: Auction): string {
    return new Date(auction.startTime).toLocaleTimeString();
  }*/

 /* public static getDate(day: TradingDay): Date {
    r*/

  public static convertDateToModel (date: Date): string {
    return date.getUTCFullYear() +
      '-' + (date.getUTCMonth() + 1) +
      '-' + (date.getUTCDate());
  }

  /*public static convertToModel (date: Date): string {
    return date.getUTCFullYear() +
      '-' + (date.getUTCMonth() + 1) +
      '-' + (date.getUTCDate() + 1);
  }*/

  public static getNow() {
    return Date.now();
  }
}

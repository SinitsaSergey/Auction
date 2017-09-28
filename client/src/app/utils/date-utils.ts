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

  public static convertToModel (date: Date): string {
    return date.getUTCFullYear() +
      '-' + (date.getUTCMonth() + 1) +
      '-' + (date.getUTCDate() + 1);
  }

  public static timeToString(date: Date): string {
    const time = new Date(date);
    const hours = time.getHours();
    const minutes = time.getMinutes();
    return (hours > 9 ? hours : '0' + hours) + ':' + (minutes > 9 ? minutes : '0' + minutes);
  }
}

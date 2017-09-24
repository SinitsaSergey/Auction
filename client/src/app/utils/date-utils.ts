import {Auction} from "../model/auction";
import {TradingDay} from "../model/trading-day";

export class DateUtils {

  public static getFinishTime(auction: Auction): string {
    return new Date (auction.startTime + auction.duration).toLocaleTimeString();
  }

  public static convertStartTime (auction: Auction): string {
    return new Date(auction.startTime).toLocaleTimeString();
  }

  public static convertToTransferModel (date: Date): string {
    return date.getUTCFullYear() +
      '-' + (date.getUTCMonth() + 1) +
      '-' + (date.getUTCDate() + 1);
  }

  public static getDate(day: TradingDay): Date {
    return new Date(day.tradingDate);
  }

}

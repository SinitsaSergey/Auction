import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Auction} from '../model/auction';
import {HttpClient} from "@angular/common/http";

const AUCTION_PATH = 'api/auction/';

@Injectable()
export class AuctionService {

  constructor(private http: HttpClient) {
  }

  /*static dayIsFull(auctions: Auction[]): boolean {
    let sum = 0;
    for (const auction of auctions) {
      sum += auction.duration;
    }
    return (sum > (12 * 60) * 60 * 1000);
  }*/

  getAllForDay(date: string): Promise<Auction[]> {
    return this.http.get<Auction[]>(AUCTION_PATH + '?date=' + date)
      .toPromise();
  }

  getById(id: number): Promise<Auction> {
    return this.http.get<Auction>(AUCTION_PATH + id)
      .toPromise();
  }

  insert (auction: Auction, isQueue: boolean): Promise<Auction> {
    console.log(auction);
    return this.http.post<Auction>(AUCTION_PATH + '?queue=' + isQueue, auction)
      .toPromise();
  }

  remove (id): Promise<any> {
    return this.http.delete(AUCTION_PATH + id)
      .toPromise();
  }

}

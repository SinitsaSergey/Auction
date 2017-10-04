import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Auction} from '../model/auction';
import {HttpClient} from "@angular/common/http";
import {Status} from "../model/status";

const AUCTION_PATH = 'api/auction/';

@Injectable()
export class AuctionService {

  constructor(private http: HttpClient) {
  }

  getAllForDay(date: string): Promise<Auction[]> {
    return this.http.get<Auction[]>(AUCTION_PATH + '?date=' + date)
      .toPromise()
      .catch();
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

  getCurrentPrice (id): Promise <number> {
    return this.http.get<number>(AUCTION_PATH + id + '/price/')
      .toPromise();
  }

  placeBid (id): Promise <number> {
    return this.http.get<number>(AUCTION_PATH + id + '/bid/')
      .toPromise();
  }

  getFinishTime(id: number): Promise<number> {
    return this.http.get<number>(AUCTION_PATH + id + '/finish/')
      .toPromise()
      .catch(error => this.errorHandle(error));
  }

  private errorHandle(error): Promise<any> {
    console.log(error);
    return null;
  }
}

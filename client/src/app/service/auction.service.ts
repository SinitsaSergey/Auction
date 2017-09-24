import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Auction} from '../model/auction';
import {HttpClient} from "@angular/common/http";

const AUCTION_PATH = 'api/auction/';

@Injectable()
export class AuctionService {

  constructor(private http: HttpClient) {
  }

getAllForDay(date: string): Promise<Auction[]>{
  return this.http.get<Auction[]>(AUCTION_PATH + '?date=' + date)
    .toPromise();
}

getById (id: number): Promise<Auction> {
    return this.http.get<Auction>(AUCTION_PATH + id)
      .toPromise();
}

}

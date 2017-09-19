import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Auction} from '../model/auction';

const AUCTION_PATH = 'api/auction';

@Injectable()
export class AuctionService {

  constructor(private http: Http) {
  }

getAllForDay(date: Date): Promise<Auction[]>{
    const year = date.getFullYear();
    const month = '09';//date.getMonth();
    const day = '02';//date.getDay();
  return this.http.get(AUCTION_PATH + '?date=' + year + '-' + month + '-' + day)
    .toPromise()
    .then (response => response.json());
}

}

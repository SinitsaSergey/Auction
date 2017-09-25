import {Injectable} from '@angular/core';
import {Lot} from '../model/lot';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {HttpClient} from "@angular/common/http";

const LOT_PATH = 'api/lot/';

@Injectable()
export class LotService implements LotService {

  constructor(private http: HttpClient) {
  }

insert (lot): Promise<Lot> {
  return this.http.post<Lot>(LOT_PATH, lot)
    .toPromise();
}

getByStatus (status): Promise <Lot[]> {
    return this.http.get <Lot[]>(LOT_PATH + '?status=' + status)
      .toPromise();
}


}

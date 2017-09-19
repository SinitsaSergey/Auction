import {Injectable} from '@angular/core';
import {Lot} from '../model/lot';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

const LOT_PATH = 'api/lot';

@Injectable()
export class LotService implements LotService {

  constructor(private http: Http) {
  }

insert (lot): Promise<Lot> {
  return this.http.post(LOT_PATH, lot)
    .toPromise()
    .then(response => response.json());
}

}

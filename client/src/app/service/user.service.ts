import { Injectable } from '@angular/core';
import {Http} from '@angular/http';
import {User} from '../model/user';

const USER_PATH = 'api/user/';

@Injectable()
export class UserService {

  constructor(private http: Http) { }

  insert (user): Promise<User>{
    return this.http.post(USER_PATH, user)
      .toPromise()
      .then(response => response.json());
  }

}

import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {User} from '../model/user';
import {Authority} from "../model/authority";

const USER_PATH = 'api/user/';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  insert(user): Promise<User> {
    return this.http.post(USER_PATH, user)
      .toPromise()
      .then(response => response.json());
  }

  getByUsername(username): Promise<User> {
    return this.http.get(USER_PATH + '?username=' + username)
      .toPromise()
      .then(response => response.json());
  }

  /*getUsersByAuthority (authority): Promise<User[]>{
    return this.http.get(USER_PATH + '?authority=' + authority)
      .toPromise()
      .then(response => response.json());
  }*/

  getAllUsers(): Promise<User[]> {
    return this.http.get(USER_PATH)
      .toPromise()
      .then(response => response.json());
  }

  setAs (authority: string, user: User): Promise<User> {
    return this.http.put(USER_PATH + 'role?authority=' + authority, user)
      .toPromise()
      .then(response => response.json());
  }

  setBan (isBanned: boolean, user: User): Promise<User> {
    return this.http.put(USER_PATH + 'ban?blocked=' + isBanned, user)
      .toPromise()
      .then(response => response.json());
  }

}

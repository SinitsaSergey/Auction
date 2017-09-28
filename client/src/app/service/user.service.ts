import {Injectable, Inject} from '@angular/core';
import {Http} from '@angular/http';
import {User} from '../model/user';
import {Authority} from "../model/authority";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "./authentication.service";

const USER_PATH = 'api/user/';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  insert(user): Promise<User> {
    return this.http.post<User>(USER_PATH, user)
      .toPromise();
  }

  changePassword (newPassword): Promise<User> {
    return this.http.put<User>(USER_PATH + 'password', newPassword)
      .toPromise();
  }

 update(user): Promise<User> {
    return this.http.put<User>(USER_PATH, user)
      .toPromise();
  }

  getByUsername(username): Promise<User> {
    return this.http.get<User>(USER_PATH + '?username=' + username)
      .toPromise();
  }

  getAllUsers(): Promise<User[]> {
    return this.http.get<User[]>(USER_PATH)
      .toPromise();
  }

  setAs (authority: string, user: User): Promise<User> {
    return this.http.put<User>(USER_PATH + 'role?authority=' + authority, user)
      .toPromise();
  }

  setBan (isBanned: boolean, user: User): Promise<User> {
    return this.http.put<User>(USER_PATH + 'ban?blocked=' + isBanned, user)
      .toPromise();
  }

}

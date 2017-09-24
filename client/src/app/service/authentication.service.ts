import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {User} from "../model/user";
import {UserService} from "./user.service";
import {HttpClient} from "@angular/common/http";

const LOGIN_URL = 'api/login';
const HEADERS: Headers = new Headers({'Content-Type': 'application/json'});

@Injectable()
export class AuthenticationService {

  public token: string;
  public username = 'guest';
  currentUser: User;

  constructor(private http: HttpClient, private userService: UserService) {
    const authenticationData = JSON.parse(localStorage.getItem('authenticationData'));
    if (authenticationData) {
      this.token = authenticationData.token;
      this.username = authenticationData.username;
      this.getCurrentUser();
    }
  }

 login(username: string, password: string): Observable<boolean> {
    const body = JSON.stringify({username: username, password: password});
    return this.http.post<boolean>(LOGIN_URL, body, {observe: 'response'})
      .map(response => {
        console.log(response);
        const token = response.headers.get('Authorization').slice(7);
        if (!token) return false;
        this.token = token;
        this.username = username;
        localStorage.setItem('authenticationData', JSON.stringify({username: username, token: token}));
        return true;
        }
      );
  }

  /*login(username: string, password: string): Observable<boolean> {
    const body = JSON.stringify({username: username, password: password});
    const options = new RequestOptions({headers: HEADERS});
    return this.http.post(LOGIN_URL, body, options)
      .map(response => {
          const token = response.headers.get('Authorization').slice(7);
          if (!token) return false;
          this.token = token;
          this.username = username;
          localStorage.setItem('authenticationData', JSON.stringify({username: username, token: token}));
          return true;
        }
      );
  }*/

  logout(): void {
    this.token = null;
    this.username = 'guest';
    this.currentUser = null;
    localStorage.removeItem('authenticationData');
  }

  getCurrentUser(): void {
    this.userService.getByUsername(this.username)
      .then(user => this.currentUser = user);
  }

}

import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

const LOGIN_URL = 'api/login';
//const HEADERS: Headers = new Headers({'Content-Type': 'application/json'});

@Injectable()
export class AuthenticationService {

  public token: string;
  public username = 'guest';

  constructor(private http: Http) {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log('currentUser ' + currentUser);
    if (currentUser) {
      this.token = currentUser.token;
      this.username = currentUser.username;
    }
  }

  login(username: string, password: string): Observable<boolean> {
    console.log('cred' + username + ' ' + password);
    const body = JSON.stringify({username: username, password: password});
    const options = new RequestOptions({headers: new Headers({'Content-Type': 'application/json'})});
    return this.http.post(LOGIN_URL, body, options)
      .map(response => {
        console.log(response.headers.get('Authorization'));
        const token = response.headers.get('Authorization').slice(7);
        if (!token) return false;
        this.token = token;
        this.username = username;
        localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));
        return true;
        }
      );
  }

  logout(): void {
    this.token = null;
    this.username = 'guest';
    localStorage.removeItem('currentUser');
  }

}

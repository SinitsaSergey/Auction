import {Observable} from 'rxjs/Observable';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class JWTInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authData = JSON.parse(localStorage.getItem('authenticationData'));
    if (authData) {
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + authData.token)
      });
    }
    req = req.clone({
      headers: req.headers.set('Content-Type', 'application/json')
    });
    return next.handle(req);
  }

}

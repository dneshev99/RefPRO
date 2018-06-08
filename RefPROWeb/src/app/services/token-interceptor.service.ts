import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.toString() === 'http://api2.tues.dreamix.eu:80/login') {
      return next.handle(req);
    }

    const tokenizedReq = req.clone({
        setHeaders: {
          Authorization: localStorage.getItem('AuthToken')
        }
    });

    return next.handle(tokenizedReq);


  }

}

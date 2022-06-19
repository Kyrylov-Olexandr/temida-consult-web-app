import {Injectable} from '@angular/core';
import {UserService} from "../services/user.service";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const userAuth = this.userService.userAuthValue;
    if (userAuth && userAuth.token) {
      request = request.clone({
        setHeaders: {
          Authorization: `${userAuth.type} ${userAuth.token}`,
          'Content-Type': 'application/json'
        }
      });
    }
    return next.handle(request);
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../environments/environment';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {JwtResponse} from '../response/JwtResponse';
import {CookieService} from 'ngx-cookie-service';
import {User} from "../models/User";
import {SignUpForm} from "../value-objects/SignUpForm";
import {LoginForm} from "../value-objects/LoginForm";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userAuthSubject: BehaviorSubject<JwtResponse | null>;
  public userAuth: Observable<JwtResponse | null>;
  public nameTerms = new Subject<string>();
  public name$ = this.nameTerms.asObservable();
  public currentUser: User | undefined;

  constructor(private http: HttpClient,
              private cookieService: CookieService) {
    const userAuthDataJson = localStorage.getItem('userAuthDataJson');
    const userAuthData = JSON.parse(userAuthDataJson ? userAuthDataJson : '{}');
    this.userAuthSubject = new BehaviorSubject<JwtResponse | null>(userAuthData)
    this.userAuth = this.userAuthSubject.asObservable();
  }

  get userAuthValue() {
    if (this.userAuthSubject) {
      return this.userAuthSubject.value;
    }
    return null;
  }

  login(loginForm: LoginForm) {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtResponse>(url, loginForm, {observe: 'response'}).pipe(
      tap(response => {
        if (response.ok && response.body) {
          const userAuthData = response.body;
          const userAuthDataJson = JSON.stringify(userAuthData)
          if (loginForm.remembered) {
            localStorage.setItem('userAuthDataJson', userAuthDataJson);
          }
          this.cookieService.set('userAuthDataJson', userAuthDataJson);
          this.nameTerms.next(userAuthData.name);
          this.userAuthSubject.next(userAuthData);
          return userAuthData;
        }
        return
      })
    )
  }

  signUp(signUpForm: SignUpForm) {
    const url = `${apiUrl}/signup`;
    return this.http.post(url, signUpForm, {observe: 'response'})
  }

  updateUserDetails(user:User) {
    const url = `${apiUrl}/profile`;
    return this.http.put(url, user, {observe: 'response'})
  }

  logout() {
    this.userAuthSubject.next(null);
    localStorage.removeItem('userAuthDataJson');
    this.cookieService.delete('userAuthDataJson');
  }

  getUserDataByEmail(email: string): Observable<User> {
    const url = `${apiUrl}/user?email=${email}`;
    return this.http.get<User>(url)
  }

  getAllUsers(filterText: string = ""): Observable<Array<User>> {
    const url = `${apiUrl}/users?filterText=${filterText}`;
    return this.http.get<Array<User>>(url)
  }

  deleteUser(userId: number) {
    const url = `${apiUrl}/users?id=${userId}`;
    return this.http.delete(url)
  }

}

import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../Model/user';

const headers = new HttpHeaders().set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private base_URL = 'http://localhost:8080/api/auth/';

  userRoles: string = '';
  constructor(private http: HttpClient, private router: Router) {}
  signup(user: User): Observable<any> {
    //console.log('In AuthService');
    return this.http
      .post(this.base_URL + 'signup', user, { headers, responseType: 'text' })
      .pipe(catchError(this.handleError));
  }

  uniqueUsername(username: string): Observable<any> {
    return this.http.get(this.base_URL + 'checkusername/' + username);
  }

  uniqueUserEmail(email: string): Observable<any> {
    return this.http.get(this.base_URL + 'checkuseremail/' + email);
  }

  login(user: string, password: string) {
    // console.log('In AuthService -  login');
    return this.http
      .post<any>(
        this.base_URL + 'login',
        { username: user, password: password },
        { headers }
      )
      .pipe(
        catchError(this.handleError),
        map((userData) => {
          sessionStorage.setItem('username', user);
          let tokenStr = 'Bearer ' + userData.token;
          console.log('Token---  ' + tokenStr);
          sessionStorage.setItem('id', userData.id);
          sessionStorage.setItem('token', tokenStr);
          sessionStorage.setItem('roles', JSON.stringify(userData.roles));
          sessionStorage.setItem('email', userData.email);
          sessionStorage.setItem('phone', userData.phone);
          sessionStorage.setItem('address', userData.address);
          sessionStorage.setItem('avatar', userData.avatar);
          sessionStorage.setItem('balance', userData.balance);
          sessionStorage.setItem('dob', userData.dob);
          sessionStorage.setItem('status', userData.status);
          // console.log(userData);
          return userData;
        })
      );
  }

  logout() {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }

  isAdmin(): boolean {
    this.userRoles = sessionStorage.getItem('roles')!;
    if (this.userRoles.includes('ROLE_ADMIN')) {
      return true;
    } else return false;
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('username') !== null;
  }

  private handleError(httpError: HttpErrorResponse) {
    let message: string = '';

    if (httpError.error instanceof ProgressEvent) {
      console.log('in progrss event');
      message = 'Network error';
    } else {
      message = httpError.error;
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${httpError.status}, ` +
          `body was: ${httpError.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError(message);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.base_URL}signup/${id}`);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.base_URL}signup/${id}`, user);
  }

  cartSubject = new Subject<any>();

  cartItem = new Subject<any>();

  totalCart = new Subject<any>();
}

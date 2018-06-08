import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {HomeComponent} from '../home/home.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
  }

  attemptLogin() {
    if (this.username === '' || this.password === '') {
      alert('Username and password cannot be blank!');
      this.username = '';
      this.password = '';
      return;
    }


    const headers = new HttpHeaders();
    headers.append('Access-Control-Expose-Headers', 'Authorization');
    this.httpClient.post('http://api2.tues.dreamix.eu:80/login',
      { username : this.username,
              password: this.password},
      {observe: 'response', headers: headers}).subscribe(response => {
          if (response.ok) {
            localStorage.setItem('AuthToken', response.headers.get('Authorization'));
           HomeComponent.isLoginVisible = !HomeComponent.isLoginVisible;
         } else if (response.status === 401) {
            alert('Invalid username or password!');
          }
        },
        error => {
          alert('Error occurred try again later!');
        }
       );
  }
}

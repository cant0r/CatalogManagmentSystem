import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  badResponse = false;

  username: string = "";
  password: string = "";

  loginAPI = "http://localhost:8080/cms/api/login"

  constructor(private router: Router,
    private httpClient: HttpClient) { }

  ngOnInit(): void {
  }

  onClick() {
    this.httpClient.post<Observable<boolean>>(this.loginAPI, {
      username: this.username,
      password: this.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.username + ':' + this.password)
        );
        this.router.navigateByUrl("/containers")
      } else {
        this.badResponse = true;
      }
    });
  }

}

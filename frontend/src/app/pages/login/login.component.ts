import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {LoginForm} from "../../value-objects/LoginForm";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
  }

  login(form: NgForm) {
    const loginForm = new LoginForm(form.value)
    this.userService.login(loginForm).subscribe(
      response => {
        this.userService.getUserDataByEmail(response.body!.account)
          .subscribe(user => {
            this.userService.currentUser = user;
            this.router.navigate(["cabinet/profile"])
          });
      },
      err => {
        console.log("invalid login or password")
      }
    )
  }

}

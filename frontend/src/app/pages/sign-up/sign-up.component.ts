import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {SignUpForm} from "../../value-objects/SignUpForm";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) {

  }
  ngOnInit(): void {
  }

  singUp(form: NgForm) {
    const signUpForm = new SignUpForm(form.value);
    if (signUpForm.isValid()) {
      this.userService.signUp(signUpForm)?.subscribe(response => {
        if (response.ok) {
          this.router.navigate(['/login']);
        }
      });
    }
  }
}

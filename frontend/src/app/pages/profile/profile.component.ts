import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {NgForm} from "@angular/forms";
import {User} from "../../models/User";
import {CabinetComponent} from "../cabinet/cabinet.component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User;


  constructor(private userService: UserService,
              private cabinetComponent: CabinetComponent) {
    this.user = this.userService.currentUser!;
  }

  ngOnInit(): void {
    this.cabinetComponent.sectionName = "Персональні дані";
  }

  saveProfile(form: NgForm) {
    const user = new User(form.value)
    this.userService.updateUserDetails(user).subscribe(
      response => {},
      error => {}
    )
  }


}

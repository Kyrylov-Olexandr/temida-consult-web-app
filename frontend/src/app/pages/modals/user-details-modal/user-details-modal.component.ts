import { Component, OnInit } from '@angular/core';
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {ProfileComponent} from "../../profile/profile.component";
import {NgForm} from "@angular/forms";
import {User} from "../../../models/User";

@Component({
  selector: 'app-user-details-modal',
  templateUrl: './user-details-modal.component.html',
  styleUrls: ['./user-details-modal.component.css']
})
export class UserDetailsModalComponent {

  user: User = new User();

  constructor(public modalRef: MdbModalRef<UserDetailsModalComponent>,
              ) {}

}






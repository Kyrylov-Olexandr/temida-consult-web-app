import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ProfileComponent} from "../profile/profile.component";
import {CabinetComponent} from "../cabinet/cabinet.component";
import {UserDetailsModalComponent} from "../modals/user-details-modal/user-details-modal.component";

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {

  users!: Array<User>;
  filterText: string = "";
  userDetailsModalRef: MdbModalRef<UserDetailsModalComponent> | null = null;

  constructor(private userService: UserService,
              private cabinetComponent: CabinetComponent,
              private modalService: MdbModalService ) {
    this.userService.getAllUsers().subscribe(
      response => { this.users = response; },
      error => {}
    )}

  ngOnInit(): void {
    this.cabinetComponent.sectionName = "Користувачі"
  }

  findByFilter(): void {
    this.userService.getAllUsers(this.filterText).subscribe(
      response => { this.users = response; },
      error => {}
    )
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe();
  }

  viewUser(user: User) {
    this.userDetailsModalRef = this.modalService.open(UserDetailsModalComponent, {
      modalClass: 'modal-xl',
      data: {
        user: user
      }
    })
  }


}

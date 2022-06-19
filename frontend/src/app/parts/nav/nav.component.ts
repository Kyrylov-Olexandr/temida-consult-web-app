import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {JwtResponse} from "../../response/JwtResponse";
import {ViewportScroller} from "@angular/common";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  currentUserAuthSubscription: Subscription | undefined;
  name$: Subscription | undefined;
  name: string | undefined;
  userAuth: JwtResponse | null | undefined;
  root = '/';

  constructor(private userService: UserService,
              private scroller: ViewportScroller) {}

  ngOnInit(): void {
    this.name$ = this.userService.name$.subscribe(aName => this.name = aName);
    this.currentUserAuthSubscription = this.userService.userAuth.subscribe(user => {
      this.userAuth = user?.name ? user : null;
    });
  }

  ngOnDestroy(): void {
    this.currentUserAuthSubscription?.unsubscribe();;
  }

  logout() {
    this.userService.logout();
  }

  scrollTo(sectionId: string) {
    this.scroller.scrollToAnchor(sectionId)
  }


}

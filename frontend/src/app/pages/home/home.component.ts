import { Component, OnInit } from '@angular/core';
import {ViewportScroller} from "@angular/common";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor( private scroller: ViewportScroller) { }

  ngOnInit(): void {
  }

  scrollTo(sectionId: string) {
    this.scroller.scrollToAnchor(sectionId)
  }
}

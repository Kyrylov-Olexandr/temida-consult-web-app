import { Component, OnInit } from '@angular/core';
import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {AdvertisementConfigService} from "../../../services/advertisement-config.service";
import {AdvertisementConfig} from "../../../models/AdvertisementConfig";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-adv-config-modal',
  templateUrl: './adv-config-modal.component.html',
  styleUrls: ['./adv-config-modal.component.css']
})
export class AdvConfigModalComponent implements OnInit {

  //configs: Array<AdvertisementConfig> = [];
  selectedConfig: AdvertisementConfig = new AdvertisementConfig();

  constructor(public modalRef: MdbModalRef<AdvConfigModalComponent>,
              private advConfigService: AdvertisementConfigService) {
  }

  ngOnInit(): void {
  }

  save() {
    console.log('test')
    this.advConfigService.save(this.selectedConfig).subscribe();
  }

}

import {Component, OnInit, ViewChild} from '@angular/core';
import {CabinetComponent} from "../cabinet/cabinet.component";
import {Advertisement} from "../../models/Advertisement";
import {MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {UserDetailsModalComponent} from "../modals/user-details-modal/user-details-modal.component";
import {AdvConfigModalComponent} from "./adv-config-modal/adv-config-modal.component";
import {User} from "../../models/User";
import {AdvertisementService} from "../../services/advertisement.service";
import {NgForm} from "@angular/forms";
import {AdvertisementConfig} from "../../models/AdvertisementConfig";
import {AdvertisementConfigService} from "../../services/advertisement-config.service";


@Component({
  selector: 'app-advertisements',
  templateUrl: './advertisements.component.html',
  styleUrls: ['./advertisements.component.css']
})
export class AdvertisementsComponent implements OnInit {

  advConfigModalRef: MdbModalRef<AdvConfigModalComponent> | null = null;

  public advertisements: Array<Advertisement> = [];

  public advConfigs: Array<AdvertisementConfig> = [];

  public selectedConfigId: number | undefined;

  selectedAdv: Advertisement;

  @ViewChild('advListContainer') advListContainer: any;

  constructor(private cabinetComponent: CabinetComponent,
              private modalService: MdbModalService,
              private advertisementService: AdvertisementService,
              private advConfigService: AdvertisementConfigService) {
    this.selectedAdv = new Advertisement();
  }

  ngOnInit(): void {
    this.cabinetComponent.sectionName = 'Реклама';
    this.findAll();
    this.findAllConfigs();
  }

  ngAfterViewInit() {
    this.advListContainer.toggle()
  }


  openConfig() {
    if (this.selectedAdv.configId !== undefined) {
      console.log(this.advConfigs.find(el => el.id === this.selectedAdv.configId))
      this.advConfigModalRef = this.modalService.open(AdvConfigModalComponent, {
        modalClass: 'modal-dialog-centered',
        data: {
          selectedConfig: this.selectedAdv.configId === 0
            ? new AdvertisementConfig()
            : this.advConfigs.find(el => el.id === this.selectedAdv.configId),
        }
      })
    }
  }

  findAll() {
    this.advertisementService.findAll().subscribe(result => {
      this.advertisements = result;
    })
  }

  findAllConfigs() {
    this.advConfigService.findAll().subscribe(result => {
      this.advConfigs = result;
    })
  }

  saveAdvertisement(form: NgForm) {
    const adv = new Advertisement(form.value)
    this.advertisementService.save(adv).subscribe(
      response => {
        if (response.ok) {
          if (response.body != null) {
            this.advertisements.push(response.body);
          }
        }
      }
      , error => {}
    );
  }


  createNewAdvertisement() {
    this.selectedAdv = new Advertisement();
  }

  openAdvertisement(adv: Advertisement) {
    this.selectedAdv = adv;
  }

  public advConfigTitle(config: AdvertisementConfig): string {
    return `${config.name} (${config.email})`;
  }
}

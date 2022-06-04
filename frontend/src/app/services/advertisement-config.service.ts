import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../../environments/environment";
import {AdvertisementConfig} from "../models/AdvertisementConfig";

@Injectable({
  providedIn: 'root'
})
export class AdvertisementConfigService {

  constructor(private http: HttpClient) {
  }

  findAll() {
    const url = `${apiUrl}/advConfig`;
    return this.http.get<Array<AdvertisementConfig>>(url);
  }

  save(config: AdvertisementConfig) {
    const url = `${apiUrl}/advConfig`;
    return this.http.post(url, config);
  }
}

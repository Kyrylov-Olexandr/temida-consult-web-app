import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {apiUrl} from "../../environments/environment";
import {Advertisement} from "../models/Advertisement";

@Injectable({
  providedIn: 'root'
})
export class AdvertisementService {

  constructor(private http: HttpClient) {

  }

  findAll() {
    const url = `${apiUrl}/advertisement`
    return this.http.get<Array<Advertisement>>(url)
  }

  save(adv: Advertisement) {
    const url = `${apiUrl}/advertisement`
    return this.http.post<Advertisement>(url, adv, {observe: 'response'});
  }

  send(advId: number) {
    const url = `${apiUrl}/advertisement/${advId}/send`;
    return this.http.post(url, {}, {observe: 'response'});
  }
}

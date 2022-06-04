import {AdvertisementSubscription} from "./AdvertisementSubscription";

export class AdvertisementConfig {

  id: number = 0;

  name: string = "";

  email: string = "";

  password: string = "";

  client: string = "gmail";

  subscriptions: Array<AdvertisementSubscription> = [];

  public constructor(init?: Partial<AdvertisementConfig>) {
    Object.assign(this, init);
  }
}

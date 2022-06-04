import {Role} from "../enum/Role";

export class Advertisement {

  id: number = 0;

  name: string = "";

  content: string = "";

  active: boolean = false;

  subject: string = "";

  configId: number = 0;

  public constructor(init?: Partial<Advertisement>) {
    Object.assign(this, init);
  }
}

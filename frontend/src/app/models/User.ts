import {Role} from "../enum/Role";

export class User {

  id: number = 0;

  email: string = "";

  password: string = "";

  firstName: string = "";

  lastName: string = "";

  phone: string = "";

  locality: string = "";

  region: string = "";

  zip: string = "";

  active: boolean = true;

  role: string = "";

  street: string = "";

  public constructor(init?: Partial<User>) {
    Object.assign(this, init);
    this.active = true;
    this.role = Role.Customer;
  }
  fullName(): string {
    return this.firstName + " " + this.lastName;
  }
}

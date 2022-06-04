import {SignUpForm} from "./SignUpForm";

export class LoginForm {

  private email: string = "";

  private password: string = ""

  private _remembered: boolean = false;

  public constructor(init?: Partial<LoginForm>) {
    Object.assign(this, init);
  }

  get remembered(): boolean {
    return this._remembered;
  }

  set remembered(value: boolean) {
    this._remembered = value;
  }
  isValid() {
    return this.password?.length > 3 && SignUpForm.EMAIL_REGEXP.test(this.email)
  }
}

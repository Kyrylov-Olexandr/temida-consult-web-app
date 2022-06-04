export class SignUpForm {

  private firstName: string = "";

  private lastName: string = "";

  private email: string = "";

  private password: string = ""

  public static EMAIL_REGEXP = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  public constructor(init?: Partial<SignUpForm>) {
    Object.assign(this, init);
  }

  isValid() {
    return this.password?.length > 3 && this.firstName !== "" && this.lastName !== "" && SignUpForm.EMAIL_REGEXP.test(this.email)
  }
}

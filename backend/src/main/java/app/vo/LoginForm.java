package app.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @Email
    private String email;

    @Size(min = 3)
    private String password;
}

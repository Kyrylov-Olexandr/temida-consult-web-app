package app.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {
    @Size(min = 3, max = 100)
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
}

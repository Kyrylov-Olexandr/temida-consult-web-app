package app.model;

import app.enums.Role;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Size(min = 3, message = "Length must be more than 3")
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique = true)
    private String phone;

    private String locality;

    private String region;

    private String zip;

    private String street;

    @NotNull
    private boolean active = true;

    private String role = Role.CUSTOMER.name();

    public String fullName() {
        return firstName + " " + lastName;
    }
}

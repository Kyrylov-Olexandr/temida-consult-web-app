package app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class AdvertisementConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String client;

    private String email;

    private String password;

//    @ManyToMany
//    private Set<Subscription> subscriptions;
}

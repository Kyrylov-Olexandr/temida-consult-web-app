package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 300)
    private String subject;

    @Column(length = 5000)
    private String content;

    @ManyToMany
    private Set<File> attachments;

    @ManyToMany
    private Set<Subscription> subscriptions;

    @ManyToOne
    private AdvertisementConfig config;

}

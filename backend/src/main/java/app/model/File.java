package app.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 300)
    private String path;
}

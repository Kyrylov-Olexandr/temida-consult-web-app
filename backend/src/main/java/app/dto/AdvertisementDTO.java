package app.dto;

import lombok.Data;

@Data
public class AdvertisementDTO{

    private Long id;

    private String name;

    private String subject;

    private String content;

    private long configId;

}

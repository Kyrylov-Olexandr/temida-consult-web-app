package app.service;




import app.model.AdvertisementConfig;

import java.util.Collection;

public interface AdvertisementConfigService {

    AdvertisementConfig save(AdvertisementConfig config);

    Collection<AdvertisementConfig> findAll();

}

package app.service.impl;


import app.model.AdvertisementConfig;
import app.repository.AdvertisementConfigRepository;
import app.service.AdvertisementConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class AdvertisementConfigServiceImpl implements AdvertisementConfigService {

    private final AdvertisementConfigRepository advConfigRepository;

    @Autowired
    public AdvertisementConfigServiceImpl(AdvertisementConfigRepository advConfigRepository) {
        this.advConfigRepository = advConfigRepository;
    }

    @Override
    public AdvertisementConfig save(AdvertisementConfig config) {
        return advConfigRepository.save(config);
    }

    @Override
    public Collection<AdvertisementConfig> findAll() {
        return advConfigRepository.findAll();
    }
}

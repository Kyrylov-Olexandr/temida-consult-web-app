package app.service.impl;


import app.model.Advertisement;
import app.repository.AdvertisementRepository;
import app.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteById(long id) {
        advertisementRepository.deleteById(id);
    }

    @Override
    public Advertisement findByName(String name) {
        return advertisementRepository.findByName(name);
    }

    @Override
    public Optional<Advertisement> findById(long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public Collection<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }
}

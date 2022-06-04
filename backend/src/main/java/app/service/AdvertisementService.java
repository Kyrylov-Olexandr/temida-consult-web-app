package app.service;

import app.model.Advertisement;

import java.util.Collection;
import java.util.Optional;

public interface AdvertisementService {

    Advertisement save(Advertisement advertisement);

    void deleteById(long id);

    Advertisement findByName(String name);

    Optional<Advertisement> findById(long id);

    Collection<Advertisement> findAll();
}

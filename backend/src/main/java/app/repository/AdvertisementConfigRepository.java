package app.repository;

import app.model.AdvertisementConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementConfigRepository extends JpaRepository<AdvertisementConfig, Long> {
}

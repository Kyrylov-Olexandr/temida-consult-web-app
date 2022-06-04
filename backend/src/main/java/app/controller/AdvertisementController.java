package app.controller;


import app.model.Advertisement;
import app.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/advertisement")
    public ResponseEntity<Advertisement> save(@RequestBody Advertisement advertisement) {
        try {
            Advertisement savedAdvertisement = advertisementService.save(advertisement);
            return ResponseEntity.ok(savedAdvertisement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/advertisement")
    public ResponseEntity deleteById(@RequestParam Long id) {
        try {
            advertisementService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/advertisement/{id}")
    public ResponseEntity<Advertisement> findById(@PathVariable long id) {
        try {
            Advertisement advertisement = advertisementService.findById(id).orElseThrow();
            return ResponseEntity.ok(advertisement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/advertisement")
    public ResponseEntity<Collection<Advertisement>> findAll() {
        try {
            Collection<Advertisement> advertisements = advertisementService.findAll();
            return ResponseEntity.ok(advertisements);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

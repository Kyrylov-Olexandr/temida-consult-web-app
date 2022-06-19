package app.controller;


import app.email_sender.MailServiceEnum;
import app.model.Advertisement;
import app.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequestMapping("/advertisement")
@CrossOrigin
@RestController
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping()
    public ResponseEntity<Advertisement> save(@RequestBody Advertisement advertisement) {
        try {
            Advertisement savedAdvertisement = advertisementService.save(advertisement);
            return ResponseEntity.ok(savedAdvertisement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteById(@RequestParam Long id) {
        try {
            advertisementService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> findById(@PathVariable long id) {
        try {
            Advertisement advertisement = advertisementService.findById(id).orElseThrow();
            return ResponseEntity.ok(advertisement);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping()
    public ResponseEntity<Collection<Advertisement>> findAll() {
            Collection<Advertisement> advertisements = advertisementService.findAll();
            return ResponseEntity.ok(advertisements);
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Object> send(@PathVariable long id) {
        try {
            Advertisement advertisement = advertisementService.findById(id).orElseThrow();
            advertisementService.send(id, advertisement.getConfig().getClient());
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException | IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        } catch (MailException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}

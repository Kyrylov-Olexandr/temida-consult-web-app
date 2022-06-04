package app.controller;


import app.model.AdvertisementConfig;
import app.service.AdvertisementConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
public class AdvertisementConfigController {

    private final AdvertisementConfigService advConfigService;

    @Autowired
    public AdvertisementConfigController(AdvertisementConfigService advConfigService) {
        this.advConfigService = advConfigService;
    }

    @GetMapping("/advConfig")
    ResponseEntity<Collection<AdvertisementConfig>> findAll() {
        try {
            Collection<AdvertisementConfig> configs = advConfigService.findAll();
            return ResponseEntity.ok(configs);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/advConfig")
    ResponseEntity<AdvertisementConfig> save(@RequestBody AdvertisementConfig config) {
        try {
            AdvertisementConfig savedConfig = advConfigService.save(config);
            return ResponseEntity.ok(savedConfig);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

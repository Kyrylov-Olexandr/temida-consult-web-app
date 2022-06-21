package app.controller;


import app.dto.AdvertisementDTO;
import app.email_sender.MailServiceEnum;
import app.model.Advertisement;
import app.service.AdvertisementService;
import app.utils.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequestMapping("/advertisement")
@CrossOrigin
@RestController
public class AdvertisementController implements Mapper<Advertisement, AdvertisementDTO> {

    private final AdvertisementService advertisementService;

    private final ModelMapper modelMapper;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, ModelMapper modelMapper) {
        this.advertisementService = advertisementService;
        this.modelMapper = modelMapper;

    }

    @PostMapping()
    public ResponseEntity<AdvertisementDTO> save(@RequestBody AdvertisementDTO advertisementDTO) {
        try {
            Advertisement advertisement = convertToEntity(advertisementDTO);
            Advertisement savedAdvertisement = advertisementService.save(advertisement);
            AdvertisementDTO savedAdvertisementDTO = convertToDTO(savedAdvertisement);
            return ResponseEntity.ok(savedAdvertisementDTO);
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
    public ResponseEntity<Collection<AdvertisementDTO>> findAll() {
            Collection<Advertisement> advertisements = advertisementService.findAll();
            Collection<AdvertisementDTO> advertisementDTOs = covertAllToDTO(advertisements);
            return ResponseEntity.ok(advertisementDTOs);
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<Object> send(@PathVariable long id) {
        try {
            Advertisement advertisement = advertisementService.findById(id).orElseThrow();
            advertisementService.send(id, advertisement.getConfig().getClient());
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException | IllegalArgumentException exception) {
            exception.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (MailException exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    public AdvertisementDTO convertToDTO(Advertisement advertisement) {
//        PropertyMap<Advertisement, AdvertisementDTO> personMap = new PropertyMap<>() {
//            protected void configure() {
//                map().setConfigId(source.getConfig().getId());
//            }
//        };
//        modelMapper.addMappings(personMap);
        return modelMapper.map(advertisement, AdvertisementDTO.class);
    }

    @Override
    public Advertisement convertToEntity(AdvertisementDTO advertisementDTO) {
        return modelMapper.map(advertisementDTO, Advertisement.class);
    }

    @Override
    public Collection<AdvertisementDTO> covertAllToDTO(Collection<Advertisement> advertisements) {
        return advertisements.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Advertisement> covertAllToEntity(Collection<AdvertisementDTO> advertisementDTOs) {
        return advertisementDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}

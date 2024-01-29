package com.v01d.edarbackend.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.v01d.edarbackend.entities.House;
import com.v01d.edarbackend.entities.Owner;
import com.v01d.edarbackend.services.CloudinaryService;
import com.v01d.edarbackend.services.HouseService;
import com.v01d.edarbackend.services.OwnerService;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5001" })
public class HouseController {
    private final HouseService houseService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private OwnerService ownerService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    // CRUD endpoints
    @PostMapping
    public ResponseEntity<House> saveHouse(@RequestBody House house) throws IOException {

        String base64Image = house.getPhoto().split(",")[1]; // Remove the "data:image/png;base64," prefix
        byte[] imageBytes = Base64.decodeBase64(base64Image);

        File tempFile = File.createTempFile("temp-image", ".png");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(imageBytes);
        }

        String imageUrl = cloudinaryService.uploadImage(tempFile);

        house.setPhoto(imageUrl);

        System.out.println(house);

        Optional<Owner> owner = ownerService.findByEmail(house.getOwner_email());

        house.setOwner(owner.get());
        return houseService.saveHouse(house);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Integer id) {
        return houseService.getHouseById(id);
    }

    @GetMapping
    public ResponseEntity<List<House>> getAllHouses() {
        return houseService.getAllHouses();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseById(@PathVariable Integer id) {
        return houseService.deleteHouseById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Integer id, @RequestBody House updatedEntity) throws FileNotFoundException, IOException {

        String base64Image = updatedEntity.getPhoto().split(",")[1]; // Remove the "data:image/png;base64," prefix
        byte[] imageBytes = Base64.decodeBase64(base64Image);

        File tempFile = File.createTempFile("temp-image", ".png");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(imageBytes);
        }

        String imageUrl = cloudinaryService.uploadImage(tempFile);

        updatedEntity.setPhoto(imageUrl);

        System.out.println(updatedEntity);

        Optional<Owner> owner = ownerService.findByEmail(updatedEntity.getOwner_email());

        updatedEntity.setOwner(owner.get());
        
        return houseService.updateHouse(id, updatedEntity);
    }
}

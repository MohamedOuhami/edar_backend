package com.v01d.edarbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.edarbackend.entities.House;
import com.v01d.edarbackend.repositories.HouseRepository;

import org.springframework.http.ResponseEntity;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    // CRUD methods
    public ResponseEntity<House> saveHouse(House house) {
        House savedEntity = houseRepository.save(house);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<House> getHouseById(Integer id) {
        House foundEntity = houseRepository.findById(id).orElse(null);
        return foundEntity != null ? ResponseEntity.ok(foundEntity) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<House>> getAllHouses() {
        List<House> entities = houseRepository.findAll();
        return ResponseEntity.ok(entities);
    }

    public ResponseEntity<Void> deleteHouseById(Integer id) {
        houseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<House> updateHouse(Integer id, House updatedEntity) {
        if (!houseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedEntity.setId(id);
        House result = houseRepository.save(updatedEntity);
        return ResponseEntity.ok(result);
    }
}
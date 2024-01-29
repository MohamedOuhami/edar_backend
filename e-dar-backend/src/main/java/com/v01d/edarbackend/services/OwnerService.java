package com.v01d.edarbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v01d.edarbackend.entities.Owner;
import com.v01d.edarbackend.repositories.OwnerRepository;

import org.springframework.http.ResponseEntity;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // CRUD methods
    public ResponseEntity<Owner> saveOwner(Owner owner) {
        Owner savedEntity = ownerRepository.save(owner);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<Owner> getOwnerById(Integer id) {
        Owner foundEntity = ownerRepository.findById(id).orElse(null);
        return foundEntity != null ? ResponseEntity.ok(foundEntity) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> entities = ownerRepository.findAll();
        return ResponseEntity.ok(entities);
    }

    public Optional<Owner> findByEmail(String email){
        return ownerRepository.findByEmail(email);
    }

    public ResponseEntity<Void> deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Owner> updateOwner(Integer id, Owner updatedEntity) {
        if (!ownerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedEntity.setId(id);
        Owner result = ownerRepository.save(updatedEntity);
        return ResponseEntity.ok(result);
    }
}
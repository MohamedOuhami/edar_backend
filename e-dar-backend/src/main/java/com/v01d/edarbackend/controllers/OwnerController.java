package com.v01d.edarbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.v01d.edarbackend.entities.Owner;
import com.v01d.edarbackend.services.OwnerService;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin(origins={"http://localhost:3000","http://localhost:5001"})
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // CRUD endpoints
    @PostMapping
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        
        Optional<Owner> userExists = ownerService.findByEmail(owner.getEmail());

        if(userExists.isPresent()){
            return ResponseEntity.ok(userExists.get());
        }

        
        return ownerService.saveOwner(owner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Integer id) {
        return ownerService.getOwnerById(id);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable Integer id) {
        return ownerService.deleteOwnerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Integer id, @RequestBody Owner updatedEntity) {
        return ownerService.updateOwner(id, updatedEntity);
    }
}

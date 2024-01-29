package com.v01d.edarbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.edarbackend.entities.Owner;
import java.util.Optional;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    // Example custom query method
    // List<Owner> findBySomeCondition(String condition);

    Optional<Owner> findByEmail(String email);
}
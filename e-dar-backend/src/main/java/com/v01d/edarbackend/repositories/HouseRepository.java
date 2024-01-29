package com.v01d.edarbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v01d.edarbackend.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
    // Example custom query method
    // List<House> findBySomeCondition(String condition);
}
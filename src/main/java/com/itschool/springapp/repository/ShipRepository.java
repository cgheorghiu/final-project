package com.itschool.springapp.repository;

import com.itschool.springapp.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findShipByPier_Id(long pierId);

    List<Ship> findAllByName(String name);
}

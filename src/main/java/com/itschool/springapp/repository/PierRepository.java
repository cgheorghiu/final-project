package com.itschool.springapp.repository;

import com.itschool.springapp.entity.Pier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PierRepository extends JpaRepository<Pier, Long> {
}

package com.example.rent.repository;

import com.example.rent.models.Arendator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArendatorRepository extends JpaRepository<Arendator, Long> {
    
}
package com.example.lab1AUI.repository;

import com.example.lab1AUI.entity.Prison;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PrisonRepository extends JpaRepository<Prison,String> {
    Optional<Prison> findPrisonByName(String id);


}

package com.example.lab1AUI.prison.repository;

import com.example.lab1AUI.prison.entity.Prison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PrisonRepository extends JpaRepository<Prison,String> {
//    Optional<Prison> findPrisonByName(String id);


}

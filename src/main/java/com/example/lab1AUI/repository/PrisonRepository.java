package com.example.lab1AUI.repository;

import com.example.lab1AUI.Storage;
import com.example.lab1AUI.baserepository.Repository;
import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonerService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PrisonRepository extends JpaRepository<Prison,String> {
    Optional<Prison> find(String id);

    List<Prison> findAll();

}

package com.example.lab1AUI.repository;

import com.example.lab1AUI.Storage;
import com.example.lab1AUI.baserepository.Repository;
import com.example.lab1AUI.entity.Prisoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PrisonerRepository extends JpaRepository<Prisoner, Integer> {
    Optional<Prisoner> find(Integer id);
    List<Prisoner> findAll();
}

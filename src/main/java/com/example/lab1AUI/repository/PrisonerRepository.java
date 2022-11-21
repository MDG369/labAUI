package com.example.lab1AUI.repository;

import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PrisonerRepository extends JpaRepository<Prisoner, Integer> {
    Optional<Prisoner> findPrisonerById(Integer id);
    List<Prisoner> findAllByPrison(Prison Prison);
    Optional<Prisoner> findPrisonerByIdAndPrisonName(Integer id, String prison_name);
}

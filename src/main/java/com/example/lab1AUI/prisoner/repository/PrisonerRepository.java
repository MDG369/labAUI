package com.example.lab1AUI.prisoner.repository;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PrisonerRepository extends JpaRepository<Prisoner, Integer> {
    List<Prisoner> findAllByPrison(Prison Prison);
    Optional<Prisoner> findByIdAndPrison(Integer id, Prison prison);
}

package com.example.lab1AUI.prisoner.service;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prison.repository.PrisonRepository;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import com.example.lab1AUI.prisoner.repository.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrisonerService {
    private PrisonerRepository repository;
    private PrisonRepository prisonRepository;
    @Autowired
    public PrisonerService(PrisonerRepository repository, PrisonRepository prisonRepository){
        this.repository = repository;
        this.prisonRepository = prisonRepository;
    }
    public Optional<Prisoner> find(Integer id) {return repository.findById(id);}
    public Optional<Prisoner> find(Prison tower, Integer id) {
        return repository.findByIdAndPrison(id, tower);
    }
    /**
     * Creates new prisoner.
     *
     * prisoner new prisoner
     */
    public List<Prisoner> findAll() { return repository.findAll();}

    public List<Prisoner> findAll(Prison pris) {
        return repository.findAllByPrison(pris);
    }
    public Optional<Prisoner> find(Integer id, String prison_name){
        Optional<Prison> prison = prisonRepository.findById(prison_name);
        if (prison.isPresent()) {
            return repository.findByIdAndPrison(id, prison.get());
        } else {
            return Optional.empty();
        }

    }
    public Prisoner create(Prisoner prisoner) {
        return repository.save(prisoner);
    }

    /**
     * Updates existing prisoner.
     *
     * @param prisoner prisoner to be updated
     */
    @Transactional
    public void update(Prisoner prisoner) {
        repository.save(prisoner);
    }

    /**
     * Deletes existing prisoner.
     *
     * existing prisoner's id to be deleted
     */
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}

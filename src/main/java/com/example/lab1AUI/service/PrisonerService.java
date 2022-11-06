package com.example.lab1AUI.service;

import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.repository.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrisonerService {
    private PrisonerRepository repository;

    @Autowired
    public PrisonerService(PrisonerRepository repository){this.repository = repository;}
    public Optional<Prisoner> find(Integer id) {return repository.find(id);}


    /**
     * Creates new prisoner.
     *
     * prisoner new prisoner
     */
    public List<Prisoner> findAll() { return repository.findAll();}

    public void create(Prisoner prisoner) {
        repository.save(prisoner);
    }

    /**
     * Updates existing prisoner.
     *
     * @param prisoner prisoner to be updated
     */
    public void update(Prisoner prisoner) {
        repository.save(prisoner);
    }

    /**
     * Deletes existing prisoner.
     *
     * existing prisoner's id to be deleted
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}

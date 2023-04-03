package com.example.lab1AUI.prison.service;

import com.example.lab1AUI.prison.entity.Prison;

import com.example.lab1AUI.prison.repository.PrisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class PrisonService {

    private PrisonRepository repository;

    @Autowired
    public PrisonService(PrisonRepository repository) {
        this.repository = repository;

    }

    public Optional<Prison> find(String name) {return repository.findById(name);}
    public List<Prison> findAll(){return repository.findAll();}
    public void create(Prison prison) {
        repository.save(prison);

    }
    @Transactional
    public void delete(Prison prison) {
        repository.delete(prison);

    }
    @Transactional
    public void update(Prison prison ){
        repository.save(prison);}
}

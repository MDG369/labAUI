package com.example.lab1AUI.service;

import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.repository.PrisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class PrisonService {

    private PrisonRepository repository;
    @Autowired
    public PrisonService(PrisonRepository repository) {this.repository = repository;}
    public Optional<Prison> find(String name) {return repository.findPrisonByName(name);}
    public Prison create(Prison prison) {return repository.save(prison);}
    public void delete(Prison prison) {repository.delete(prison);}
}

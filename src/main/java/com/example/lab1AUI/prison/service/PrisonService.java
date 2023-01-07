package com.example.lab1AUI.prison.service;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prison.event.repository.PrisonEventRepository;
import com.example.lab1AUI.prison.repository.PrisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class PrisonService {

    private PrisonRepository repository;
    private PrisonEventRepository eventRepository;
    @Autowired
    public PrisonService(PrisonRepository repository, PrisonEventRepository prisonEventRepository) {
        this.repository = repository;
        this.eventRepository = prisonEventRepository;
    }

    public Optional<Prison> find(String name) {return repository.findById(name);}
    public List<Prison> findAll(){return repository.findAll();}
    public void create(Prison prison) {
        repository.save(prison);
        eventRepository.create(prison);
    }
    @Transactional
    public void delete(Prison prison) {
        repository.delete(prison);
        eventRepository.delete(prison);
    }
    @Transactional
    public void update(Prison prison ){
        repository.save(prison);}
}

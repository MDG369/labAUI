package com.example.lab1AUI.repository;

import com.example.lab1AUI.Storage;
import com.example.lab1AUI.baserepository.Repository;
import com.example.lab1AUI.entity.Prisoner;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class PrisonerRepository implements Repository<Prisoner, Integer> {
    private Storage storage;

    @Autowired
    public PrisonerRepository(Storage storage) {this.storage = storage;}

    @Override
    public Optional<Prisoner> find(Integer id) {
        Optional<Prisoner> pris = Optional.empty();
        for(int i = 0; i < storage.prisoners.size(); i++){
            if (storage.prisoners.get(i).getId().equals(id)){
                pris = Optional.of(storage.prisoners.get(i));
            }
        }
        return pris;
    }

    @Override
    public List<Prisoner> findAll() {
        return storage.prisoners;
    }

    @Override
    public void create(Prisoner entity) {
        storage.prisoners.add(entity);
    }


    public void delete(Prisoner entity) {
        storage.prisoners.remove(entity);
    }

    @Override
    public void update(Prisoner entity) {
        storage.prisoners.remove(entity);
        storage.prisoners.add(entity);
    }

}

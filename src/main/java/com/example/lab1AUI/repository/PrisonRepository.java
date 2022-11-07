package com.example.lab1AUI.repository;

import com.example.lab1AUI.Storage;
import com.example.lab1AUI.baserepository.Repository;
import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonerService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public class PrisonRepository implements Repository<Prison, String> {
    private Storage storage;

    public PrisonRepository(Storage storage) { this.storage = storage;}

    @Override
    public Optional<Prison> find(String id) {
        Optional<Prison> pris = Optional.empty();
        for(int i = 0; i < storage.prisons.size(); i++){
            if (storage.prisons.get(i).getName().equals(id)){
                pris = Optional.of(storage.prisons.get(i));
            }
        }
        return pris;
    }

    @Override
    public List<Prison> findAll() {
        return storage.prisons;
    }

    @Override
    public void create(Prison entity) {
        storage.prisons.add(entity);
    }


    public void delete(String entity) {
        Iterator<Prisoner> it = storage.prisoners.iterator();
        while(it.hasNext()){
            Prisoner pris = it.next();
            if(pris.getPrison().getName().equals(entity)){
                it.remove();
//                storage.prisoners.get(i).setPrison(Prison.builder().name("null").build());
            }
          }
        Iterator<Prison> it2 = storage.prisons.iterator();
        while(it2.hasNext()){
            Prison pris = it2.next();
            if(pris.getName().equals(entity)){
                it2.remove();
            }
        }
    }

    @Override
    public void update(Prison entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }
}

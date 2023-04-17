package com.example.lab1AUI.configuration;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prison.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final PrisonService prisonService;
    @Autowired
    public InitializedData(PrisonService prisonService){
        this.prisonService = prisonService;
    }
    @PostConstruct
    private synchronized void init(){
        Prison alcatraz = Prison.builder()
                .name("Alcatraz")
                .size(300)
                .build();
        prisonService.create(alcatraz);
    }


}

package com.example.lab1AUI.configuration;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import com.example.lab1AUI.prisoner.service.PrisonerService;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import com.example.lab1AUI.prison.service.PrisonService;
import com.example.lab1AUI.prisoner.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final PrisonerService prisonerService;
    private final PrisonService prisonService;
    @Autowired
    public InitializedData(PrisonerService prisonerService, PrisonService prisonService){
        this.prisonerService = prisonerService;
        this.prisonService = prisonService;
    }
    @PostConstruct
    private synchronized void init(){
        Prison alcatraz = Prison.builder()
                .name("Alcatraz")
                .size(300)
                .build();
        prisonService.create(alcatraz);

        Prisoner jan = Prisoner.builder()
                .name("Jan")
                .surname("Kowalski")
                .age(34)
                .cell_number(11)
                .prison(alcatraz)
                .build();
        Prisoner bogdan = Prisoner.builder()
                .name("Bogdan")
                .surname("Nowak")
                .age(25)
                .cell_number(8)
                .prison(alcatraz)
                .build();
        Prisoner adam = Prisoner.builder()
                .name("Adam")
                .surname("Czarnecki")
                .age(30)
                .cell_number(2)
                .prison(alcatraz)
                .build();

        prisonerService.create(jan);
        prisonerService.create(bogdan);
        prisonerService.create(adam);

    }


}

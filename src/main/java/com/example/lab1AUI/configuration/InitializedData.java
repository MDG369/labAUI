package com.example.lab1AUI.configuration;

import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonService;
import com.example.lab1AUI.service.PrisonerService;
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
                .build();
        prisonService.create(alcatraz);

        Prisoner jan = Prisoner.builder()
                .id(1)
                .name("Jan")
                .surname("Kowalski")
                .age(34)
                .cell_number(11)
                .prison(alcatraz)
                .build();
        Prisoner bogdan = Prisoner.builder()
                .id(2)
                .name("Bogdan")
                .surname("Nowak")
                .age(25)
                .cell_number(8)
                .prison(alcatraz)
                .build();
        Prisoner adam = Prisoner.builder()
                .id(3)
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

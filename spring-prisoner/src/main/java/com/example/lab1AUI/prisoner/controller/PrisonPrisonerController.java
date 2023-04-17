package com.example.lab1AUI.prisoner.controller;

import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prison.service.PrisonService;
import com.example.lab1AUI.prisoner.dto.GetPrisonerResponse;
import com.example.lab1AUI.prisoner.dto.GetPrisonersResponse;
import com.example.lab1AUI.prisoner.dto.PostPrisonerRequest;
import com.example.lab1AUI.prisoner.dto.PutPrisonerRequest;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import com.example.lab1AUI.prisoner.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/prisons/{name}/prisoners")
public class PrisonPrisonerController {
    private PrisonerService prisonerService;
    private PrisonService prisonService;
    @Autowired
    public PrisonPrisonerController(PrisonerService prisonerService, PrisonService prisonService){
        this.prisonerService = prisonerService;
        this.prisonService = prisonService;
    }

    @GetMapping
    public ResponseEntity<GetPrisonersResponse> getPrisoners(@PathVariable("name") String name){
        Optional<Prison> prison = prisonService.find(name);
        return prison.map(value -> ResponseEntity.ok(GetPrisonersResponse.entityToDtoMapper().apply(prisonerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{id}")
    public ResponseEntity<GetPrisonerResponse> getPrisoner(@PathVariable("name") String name,
                                                           @PathVariable("id") Integer id){
        return prisonerService.find(id, name)
                .map(value -> ResponseEntity.ok(GetPrisonerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping
    public ResponseEntity<Void> postPrisoner(@PathVariable("name") String name,
                                              @RequestBody PostPrisonerRequest request,
                                              UriComponentsBuilder builder){
        Optional<Prison> prison = prisonService.find(name);
        if (prison.isPresent()) {
            Prisoner pris = PostPrisonerRequest
                    .dtoToEntityMapper(prison::get)
                    .apply(request);
            pris = prisonerService.create(pris);
            return ResponseEntity.created(builder.pathSegment("api", "prisons", "{name}","prisoners", "{id}")
                    .buildAndExpand(prison.get().getName(), pris.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePrisoner(@PathVariable("name") String name,
                                                @PathVariable("id") Integer id) {
        Optional<Prisoner> prisoner = prisonerService.find(id, name);
        if (prisoner.isPresent()) {
            prisonerService.delete(prisoner.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> putPrisoner(@PathVariable("name") String name,
                                             @RequestBody PutPrisonerRequest request,
                                             @PathVariable("id") Integer id){
        Optional<Prisoner> prisoner = prisonerService.find(id, name);
        if (prisoner.isPresent()) {
            PutPrisonerRequest.dtoToEntityUpdater().apply(prisoner.get(), request);
            prisonerService.update(prisoner.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

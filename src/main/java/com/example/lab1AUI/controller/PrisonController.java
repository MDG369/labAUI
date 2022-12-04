package com.example.lab1AUI.controller;

import com.example.lab1AUI.dto.*;
import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonService;
import com.example.lab1AUI.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@RestController
@RequestMapping("api/prisons")
public class PrisonController {
    private PrisonerService prisonerService;
    private PrisonService prisonService;
    @Autowired
    public PrisonController(PrisonerService prisonerService, PrisonService prisonService){
        this.prisonerService = prisonerService;
        this.prisonService = prisonService;
    }

    @GetMapping("{name}")
    public ResponseEntity<GetPrisonersResponse> getPrisoners(@PathVariable("name") String name){
        Optional<Prison> prison = prisonService.find(name);
        return prison.map(value -> ResponseEntity.ok(GetPrisonersResponse.entityToDtoMapper().apply(prisonerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{name}/{id}")
    public ResponseEntity<GetPrisonerResponse> getPrisoner(@PathVariable("name") String name,
                                                           @PathVariable("id") Integer id){
        return prisonerService.findByIdAndPrisonName(id, name)
                .map(value -> ResponseEntity.ok(GetPrisonerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping("{name}/prisoners")
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
    @DeleteMapping("{name}/{id}")
    public ResponseEntity<Void> deletePrisoner(@PathVariable("name") String name,
                                                @PathVariable("id") Integer id) {
        Optional<Prisoner> prisoner = prisonerService.find(id);
        if (prisoner.isPresent()) {
            prisonerService.delete(prisoner.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{name}/{id}")
    public ResponseEntity<Void> putPrisoner(@PathVariable("name") String name,
                                             @RequestBody PutPrisonerRequest request,
                                             @PathVariable("id") Integer id){
        Optional<Prisoner> prisoner = prisonerService.findByIdAndPrisonName(id, name);
        if (prisoner.isPresent()) {
            PutPrisonerRequest.dtoToEntityUpdater().apply(prisoner.get(), request);
            prisonerService.update(prisoner.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

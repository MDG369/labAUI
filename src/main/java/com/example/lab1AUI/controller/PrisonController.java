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
    @GetMapping
    public ResponseEntity<GetPrisonsResponse> getPrisons(){
        List<Prison> all = prisonService.findAll();
        Function<Collection<Prison>, GetPrisonsResponse> mapper = GetPrisonsResponse.entityToDtoMapper();
        GetPrisonsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
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
    @PostMapping("{name}")
    public ResponseEntity<Void> postPrisoner(@PathVariable("name") String name,
                                              @RequestBody PostPrisonerRequest request,
                                              UriComponentsBuilder builder){
        Optional<Prison> prison = prisonService.find(name);
        if (prison.isPresent()) {
            Prisoner pris = PostPrisonerRequest
                    .dtoToEntityMapper(prison::get)
                    .apply(request);
            pris = prisonerService.create(pris);
            return ResponseEntity.created(builder.pathSegment("api", "prisons", "{name}", "{id}")
                    .buildAndExpand(prison.get().getName(), pris.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Void> postPrison(@RequestBody PostPrisonRequest request,
                                           UriComponentsBuilder builder){
        Prison prison = PostPrisonRequest
                .dtoToEntityMapper()
                .apply(request);
        prison = prisonService.create(prison);
        return ResponseEntity.created(builder.pathSegment("api", "prisoner", "{name}")
                .buildAndExpand(prison.getName()).toUri()).build();
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
    @DeleteMapping("{name}")
    public ResponseEntity<Void> deletePrison(@PathVariable("name") String name){
        Optional<Prison> prison = prisonService.find(name);
        if(prison.isPresent()) {
            prisonService.delete(prison.get());
            return ResponseEntity.accepted().build();
        }else {
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

    @PutMapping("{name}")
    public ResponseEntity<Void> putPrison(@RequestBody PutPrisonRequest request, @PathVariable("name") String name){
        Optional<Prison> pris = prisonService.find(name);
        if (pris.isPresent()) {
            PutPrisonRequest.dtoToEntityUpdater().apply(pris.get(), request);
            prisonService.update(pris.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

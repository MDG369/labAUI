package com.example.lab1AUI.prisoner.controller;

import com.example.lab1AUI.prisoner.service.PrisonerService;
import com.example.lab1AUI.prisoner.dto.PutPrisonerRequest;
import com.example.lab1AUI.prisoner.dto.GetPrisonerResponse;
import com.example.lab1AUI.prisoner.dto.GetPrisonersResponse;
import com.example.lab1AUI.prisoner.dto.PostPrisonerRequest;
import com.example.lab1AUI.prisoner.dto.PutPrisonerRequest;
import com.example.lab1AUI.prisoner.entity.Prisoner;
import com.example.lab1AUI.prison.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/prisoners")
public class PrisonerController {
    private PrisonerService prisonerService;

    @Autowired
    public PrisonerController(PrisonerService prisonerService){
        this.prisonerService = prisonerService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetPrisonersResponse> getPrisoners() {
        return ResponseEntity
                .ok(GetPrisonersResponse.entityToDtoMapper().apply(prisonerService.findAll()));
    }
    @GetMapping({"{id}"})
    public ResponseEntity<GetPrisonerResponse> getPrisoner(@PathVariable("id") Integer id) {
        return prisonerService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetPrisonerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }
    @PostMapping
    public ResponseEntity<Void> postPrisoner(@RequestBody PostPrisonerRequest request, UriComponentsBuilder builder) {
        Prisoner pris = PostPrisonerRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        pris = prisonerService.create(pris);
        return ResponseEntity.created(builder.pathSegment("api", "prisoners", "{id}")
                .buildAndExpand(pris.getId()).toUri()).build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> putPrisoner(@RequestBody PutPrisonerRequest request, @PathVariable("id") Integer id){
        Optional<Prisoner> pris = prisonerService.find(id);
        if (pris.isPresent()) {
            PutPrisonerRequest.dtoToEntityUpdater().apply(pris.get(), request);
            prisonerService.update(pris.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePrisoner(@PathVariable("id") Integer id) {
        Optional<Prisoner> prisoner = prisonerService.find(id);
        if (prisoner.isPresent()) {
            prisonerService.delete(prisoner.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

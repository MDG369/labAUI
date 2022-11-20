package com.example.lab1AUI.controller;

import com.example.lab1AUI.dto.GetPrisonerResponse;
import com.example.lab1AUI.dto.GetPrisonersResponse;
import com.example.lab1AUI.dto.PostPrisonerRequest;
import com.example.lab1AUI.dto.PutPrisonerRequest;
import com.example.lab1AUI.entity.Prisoner;
import com.example.lab1AUI.service.PrisonService;
import com.example.lab1AUI.service.PrisonerService;
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
    private PrisonService prisonService;

    @Autowired
    public PrisonerController(PrisonerService prisonerService, PrisonService prisonService){
        this.prisonerService = prisonerService;
        this.prisonService = prisonService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetPrisonersResponse> getPrisoners() {
        List<Prisoner> all = prisonerService.findAll();
        Function<Collection<Prisoner>, GetPrisonersResponse> mapper = GetPrisonersResponse.entityToDtoMapper();
        GetPrisonersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }
    @GetMapping({"{id}"})
    public ResponseEntity<GetPrisonerResponse> getPrisoner(@PathVariable("id") Integer id) {
        return prisonerService.find(id)
                .map(value -> ResponseEntity.ok(GetPrisonerResponse.entityToDtoMapper().apply(value)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Void> postPrisoner(@RequestBody PostPrisonerRequest request, UriComponentsBuilder builder) {
        Prisoner pris = PostPrisonerRequest
                .dtoToEntityMapper(name -> prisonService.find(name).orElseThrow())
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
}

package com.example.lab1AUI.controller;

import com.example.lab1AUI.dto.*;
import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.service.PrisonService;
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
    private PrisonService prisonService;
    @Autowired
    public PrisonController(PrisonService prisonService){
        this.prisonService = prisonService;
    }
    @GetMapping
    public ResponseEntity<GetPrisonsResponse> getPrisons(){
        List<Prison> all = prisonService.findAll();
        Function<Collection<Prison>, GetPrisonsResponse> mapper = GetPrisonsResponse.entityToDtoMapper();
        GetPrisonsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
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

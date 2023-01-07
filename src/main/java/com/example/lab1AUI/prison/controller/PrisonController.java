package com.example.lab1AUI.prison.controller;

import com.example.lab1AUI.prison.dto.PostPrisonRequest;
import com.example.lab1AUI.prison.entity.Prison;
import com.example.lab1AUI.prison.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/prisons")
public class PrisonController {
    private PrisonService prisonService;
    @Autowired
    public PrisonController(PrisonService prisonService){
        this.prisonService = prisonService;
    }

    @PostMapping("")
    public ResponseEntity<Void> postPrison(@RequestBody PostPrisonRequest request,
                                           UriComponentsBuilder builder){
        Prison prison = PostPrisonRequest
                .dtoToEntityMapper()
                .apply(request);
        prisonService.create(prison);
        return ResponseEntity.created(builder.pathSegment("api", "prisons", "{name}")
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
}

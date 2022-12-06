package com.example.lab1AUI.event.repository;

import com.example.lab1AUI.event.dto.PostPrisonRequest;
import com.example.lab1AUI.entity.Prison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PrisonEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public PrisonEventRepository(@Value("${labAUI.prisoners.url}") String baseUrl){
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }
    public void delete(Prison prison){
        restTemplate.delete("/prisons/{name}", prison.getName());
    }
    public void create(Prison prison){
        restTemplate.postForLocation("/prisons", PostPrisonRequest.entityToDtoMapper().apply(prison));
    }
}

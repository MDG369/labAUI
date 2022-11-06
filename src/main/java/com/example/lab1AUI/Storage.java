package com.example.lab1AUI;

import com.example.lab1AUI.entity.Prison;
import com.example.lab1AUI.entity.Prisoner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Storage {
    public List<Prisoner> prisoners = new ArrayList<>();
    public List<Prison> prisons = new ArrayList<>();

}

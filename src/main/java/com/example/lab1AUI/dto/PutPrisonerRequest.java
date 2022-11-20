package com.example.lab1AUI.dto;

import com.example.lab1AUI.entity.Prison;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.example.lab1AUI.entity.Prisoner;

import java.util.function.BiFunction;
import java.util.function.Function;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PutPrisonerRequest {
    private String name;
    private String surname;
    private int age;
    private int cell_number;

   public static BiFunction<Prisoner, PutPrisonerRequest, Prisoner> dtoToEntityUpdater() {
       return (prisoner, request) -> {
           prisoner.setName(request.getName());
           prisoner.setSurname(request.getSurname());
           prisoner.setAge(request.getAge());
           prisoner.setCell_number(request.getCell_number());
           return prisoner;
       };
   }

}

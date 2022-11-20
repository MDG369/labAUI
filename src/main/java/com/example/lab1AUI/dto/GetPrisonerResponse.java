package com.example.lab1AUI.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.example.lab1AUI.entity.Prisoner;

import java.util.function.Function;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetPrisonerResponse {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private int cell_number;
    private String prison;
    public static Function<Prisoner, GetPrisonerResponse> entityToDtoMapper() {
        return prisoner -> GetPrisonerResponse.builder()
                .id(prisoner.getId())
                .name(prisoner.getName())
                .surname(prisoner.getSurname())
                .age(prisoner.getAge())
                .cell_number(prisoner.getCell_number())
                .prison(prisoner.getPrison().getName())
                .build();
    }

}

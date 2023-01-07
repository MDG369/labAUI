package com.example.lab1AUI.prisoner.dto;

import com.example.lab1AUI.prison.entity.Prison;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.example.lab1AUI.prisoner.entity.Prisoner;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PostPrisonerRequest {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private int cell_number;
    private String prison;

    public static Function<PostPrisonerRequest, Prisoner> dtoToEntityMapper(
    Supplier<Prison>prisonSupplier) {
        return request -> Prisoner.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .age(request.getAge())
                .cell_number(request.getCell_number())
                .prison(prisonSupplier.get())
                .build();
    }
}

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

import java.util.function.Function;
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
            Function<String, Prison> prisonFunction) {
        return request -> Prisoner.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .age(request.getAge())
                .cell_number(request.getCell_number())
                .prison(prisonFunction.apply(request.getPrison()))
                .build();
    }
}

package com.example.lab1AUI.prison.dto;

import com.example.lab1AUI.prison.entity.Prison;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetPrisonResponse {
    private String name;
    private int size;

    public static Function<Prison, GetPrisonResponse> entityToDtoMapper() {
        return prison -> GetPrisonResponse.builder()
                .name(prison.getName())
                .size(prison.getSize())
                .build();
    }
}

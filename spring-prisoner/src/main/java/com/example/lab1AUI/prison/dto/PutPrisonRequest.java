package com.example.lab1AUI.prison.dto;

import com.example.lab1AUI.prison.entity.Prison;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PutPrisonRequest {
    private int size;

    public static BiFunction<Prison, PutPrisonRequest, Prison> dtoToEntityUpdater() {
        return (prison, request) -> {
            prison.setSize(request.getSize());
            return prison;
        };
    }

}

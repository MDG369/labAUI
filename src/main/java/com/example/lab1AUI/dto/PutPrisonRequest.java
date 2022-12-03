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

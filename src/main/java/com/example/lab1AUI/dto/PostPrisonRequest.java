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
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PostPrisonRequest {
    private String name;
    private int size;

    public static Function<PostPrisonRequest, Prison> dtoToEntityMapper() {
        return request -> Prison.builder()
                .name(request.getName())
                .size(request.getSize())
                .build();
    }
}

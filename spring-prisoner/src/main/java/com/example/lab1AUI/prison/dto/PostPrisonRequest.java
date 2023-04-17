package com.example.lab1AUI.prison.dto;

import com.example.lab1AUI.prison.entity.Prison;
import lombok.*;

import java.util.function.Function;

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

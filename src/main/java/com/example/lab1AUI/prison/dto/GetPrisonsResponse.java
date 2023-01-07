package com.example.lab1AUI.prison.dto;

import com.example.lab1AUI.prison.entity.Prison;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetPrisonsResponse {
//    @Getter
//    @Setter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    @ToString
//    @EqualsAndHashCode
//    public static class PrisonEntry {
//        private String name;
//        private int size;
//    }

    @Singular
    private List<String> prisons;

    public static Function<Collection<Prison>, GetPrisonsResponse> entityToDtoMapper() {
        return prisoners -> {
            GetPrisonsResponseBuilder response = GetPrisonsResponse.builder();
            prisoners.stream()
                    .map(Prison::getName)
                        .forEach(response::prison);
            return response.build();
        };
    }
}

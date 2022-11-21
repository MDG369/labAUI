package com.example.lab1AUI.dto;

import com.example.lab1AUI.entity.Prison;
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
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class PrisonEntry {
        private String name;
    }

    @Singular
    private List<PrisonEntry> prisons;

    public static Function<Collection<Prison>, GetPrisonsResponse> entityToDtoMapper() {
        return prisons -> {
            GetPrisonsResponse.GetPrisonsResponseBuilder response = GetPrisonsResponse.builder();
            prisons.stream()
                    .map(prison -> PrisonEntry.builder()
                            .name(prison.getName())
                            .build())
                    .forEach(response::prison);
            return response.build();
        };
    }
}

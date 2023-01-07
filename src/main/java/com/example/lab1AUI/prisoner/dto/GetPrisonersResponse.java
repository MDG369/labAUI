package com.example.lab1AUI.prisoner.dto;


import lombok.*;
import com.example.lab1AUI.prisoner.entity.Prisoner;
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

public class GetPrisonersResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class PrisonerEntry {
        private Integer id;
        private String name;
        private String surname;
    }

    @Singular
    private List<PrisonerEntry> prisoners;

    public static Function<Collection<Prisoner>, GetPrisonersResponse> entityToDtoMapper() {
        return prisoners -> {
            GetPrisonersResponseBuilder response = GetPrisonersResponse.builder();
            prisoners.stream()
                    .map(prisoner -> PrisonerEntry.builder()
                            .id(prisoner.getId())
                            .name(prisoner.getName())
                            .surname(prisoner.getSurname())
                            .build())
                    .forEach(response::prisoner);
            return response.build();
        };
    }


}

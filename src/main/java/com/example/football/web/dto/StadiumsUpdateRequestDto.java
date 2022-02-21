package com.example.football.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StadiumsUpdateRequestDto {
    private String name;
    private String location;

    @Builder
    public StadiumsUpdateRequestDto(String name, String location) {
        this.name = name;
        this.location = location;
    }
}

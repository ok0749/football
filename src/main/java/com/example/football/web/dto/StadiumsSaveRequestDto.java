package com.example.football.web.dto;

import com.example.football.domain.stadiums.Stadiums;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StadiumsSaveRequestDto {
    private String name;
    private String location;

    @Builder
    public StadiumsSaveRequestDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Stadiums toEntity() {
        return Stadiums.builder()
                .name(name)
                .location(location)
                .build();
    }
}

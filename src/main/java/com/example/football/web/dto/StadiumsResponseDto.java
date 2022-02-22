package com.example.football.web.dto;

import com.example.football.domain.stadiums.Stadiums;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StadiumsResponseDto {
    private Long id;
    private String name;
    private String location;

    public StadiumsResponseDto(Stadiums entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.location = entity.getLocation();
    }
}

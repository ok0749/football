package com.example.football.web.dto;

import com.example.football.domain.stadiums.Stadiums;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class StadiumsListResponseDto {
    private Long id;
    private String name;
    private String location;
    private LocalDateTime modifiedDate;

    public StadiumsListResponseDto(Stadiums entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.location = entity.getLocation();
        this.modifiedDate = entity.getModifiedDate();
    }
}

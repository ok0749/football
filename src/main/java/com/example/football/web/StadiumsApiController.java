package com.example.football.web;

import com.example.football.service.stadiums.StadiumsService;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StadiumsApiController {

    private final StadiumsService stadiumsService;

    @PostMapping("/api/v1/stadiums")
    public Long save(@RequestBody StadiumsSaveRequestDto requestDto) {

        return stadiumsService.save(requestDto);
    }
}

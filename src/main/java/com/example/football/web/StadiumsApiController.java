package com.example.football.web;

import com.example.football.service.stadiums.StadiumsService;
import com.example.football.web.dto.StadiumsResponseDto;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StadiumsApiController {

    private final StadiumsService stadiumsService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/v1/stadiums")
    public Long save(@RequestBody StadiumsSaveRequestDto requestDto) {

        return stadiumsService.save(requestDto);
    }

    @PutMapping("/api/v1/stadiums/{id}")
    public Long update(@PathVariable Long id, @RequestBody StadiumsSaveRequestDto requestDto) {

        return stadiumsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/stadiums/{id}")
    public StadiumsResponseDto findById(@PathVariable Long id) {

        return stadiumsService.findById(id);
    }

    @DeleteMapping("/api/v1/stadiums/{id}")
    public Long delete(@PathVariable Long id) {
        stadiumsService.delete(id);

        return id;
    }
}

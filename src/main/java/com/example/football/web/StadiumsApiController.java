package com.example.football.web;

import com.example.football.service.stadiums.StadiumsService;
import com.example.football.web.dto.StadiumsListResponseDto;
import com.example.football.web.dto.StadiumsResponseDto;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StadiumsApiController {

    private final StadiumsService stadiumsService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/v1/stadiums")
    public JSONObject findAllDesc() {

        return stadiumsService.findAllDesc();
    }

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

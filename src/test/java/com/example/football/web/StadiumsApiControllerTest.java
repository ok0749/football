package com.example.football.web;

import com.example.football.domain.stadiums.Stadiums;
import com.example.football.domain.stadiums.StadiumsRepository;
import com.example.football.web.dto.StadiumsResponseDto;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import com.example.football.web.dto.StadiumsUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StadiumsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StadiumsRepository stadiumsRepository;

    @AfterEach
    public void cleanup() throws Exception {
        stadiumsRepository.deleteAll();
    }

    @Test
    public void Stadiums_등록() throws Exception {
        // given
        String name = "에어풋살파크";
        String location = "부산광역시 강서구 강동동 1592";
        StadiumsSaveRequestDto requestDto = StadiumsSaveRequestDto
                .builder()
                .name(name)
                .location(location)
                .build();
        String url = "http://localhost:" + port + "/api/v1/stadiums";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Stadiums> all = stadiumsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getLocation()).isEqualTo(location);
    }

    @Test
    public void Stadiums_수정() throws Exception {
        // given
        Stadiums savedStadiums = stadiumsRepository.save(
                Stadiums
                        .builder()
                        .name("에어풋살파크")
                        .location("부산광역시 강서구 강동동 1592")
                        .build()
        );

        Long id = savedStadiums.getId();
        String newName = "정관일루미네이션";
        String newLocation = "부산시 정관";

        StadiumsUpdateRequestDto requestDto = StadiumsUpdateRequestDto
                .builder()
                .name(newName)
                .location(newLocation)
                .build();

        String url = "http://localhost:" + port + "/api/v1/stadiums/" + id;

        HttpEntity<StadiumsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Stadiums> all = stadiumsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(newName);
        assertThat(all.get(0).getLocation()).isEqualTo(newLocation);
    }

    @Test
    public void stadiums_조회() {

        // given
        Stadiums savedStadiums = stadiumsRepository.save(
                Stadiums
                        .builder()
                        .name("에어풋살파크")
                        .location("부산광역시 강서구 강동동 1592")
                        .build()
        );

        Long id = savedStadiums.getId();

        String url = "http://localhost:" + port + "/api/v1/stadiums/" + id;

        // when
        ResponseEntity<String> response = restTemplate.getForEntity(
                url,
                String.class
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            assertThat(jsonObject.get("name")).isEqualTo("에어풋살파크");
            assertThat(jsonObject.get("location")).isEqualTo("부산광역시 강서구 강동동 1592");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stadiums_삭제() {

        // given
        Stadiums savedStadiums = stadiumsRepository.save(
                Stadiums
                        .builder()
                        .name("에어풋살파크")
                        .location("부산광역시 강서구 강동동 1592")
                        .build()
        );

        Long id = savedStadiums.getId();

        String url = "http://localhost:" + port + "/api/v1/stadiums/" + id;

        // when
        restTemplate.delete(url);

        // then
        assertThat(stadiumsRepository.findById(id)).isEmpty();
    }
}
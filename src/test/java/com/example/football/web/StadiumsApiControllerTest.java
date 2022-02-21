package com.example.football.web;

import com.example.football.domain.stadiums.Stadiums;
import com.example.football.domain.stadiums.StadiumsRepository;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
}
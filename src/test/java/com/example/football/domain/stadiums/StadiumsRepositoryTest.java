package com.example.football.domain.stadiums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StadiumsRepositoryTest {

    @Autowired
    StadiumsRepository stadiumsRepository;

    @AfterEach
    public void cleanup() {
        stadiumsRepository.deleteAll();
    }

    @Test
    public void 경기장저장_불러오기() {
        // given
        String name = "에어풋살파크";
        String location = "부산광역시 강서구 강동동 1592";

        stadiumsRepository.save(
                Stadiums.builder()
                        .name(name)
                        .location(location)
                        .build());

        // when
        List<Stadiums> stadiumsList = stadiumsRepository.findAll();

        // then
        Stadiums stadiums = stadiumsList.get(0);
        assertThat(stadiums.getName()).isEqualTo(name);
        assertThat(stadiums.getLocation()).isEqualTo(location);
    }
}

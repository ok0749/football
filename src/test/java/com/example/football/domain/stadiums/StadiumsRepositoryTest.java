package com.example.football.domain.stadiums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
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

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2022,2,22,10,18,50);
        stadiumsRepository.save(Stadiums
                .builder()
                .name("에어풋살파크")
                .location("부산광역시 강서구 강동동 1592")
                .build());

        // when
        List<Stadiums> all = stadiumsRepository.findAll();

        // then
        Stadiums stadiums = all.get(0);

        System.out.println(">>>>> createdDate= " + stadiums.getCreatedDate()
                + ", modifiedDate= " + stadiums.getModifiedDate());

        assertThat(stadiums.getCreatedDate()).isAfter(now);
        assertThat(stadiums.getModifiedDate()).isAfter(now);
    }

    @Test
    public void stadiums_전체내림차순조회() {
        // given
        stadiumsRepository.save(Stadiums
                .builder()
                .name("에어풋살파크1")
                .location("부산광역시 강서구 강동동 1592")
                .build());

        stadiumsRepository.save(Stadiums
                .builder()
                .name("에어풋살파크2")
                .location("부산광역시 강서구 강동동 1592")
                .build());

        // when
        List<Stadiums> all = stadiumsRepository.findAllDesc();

        // then
        assertThat(all.size()).isEqualTo(2L);
        assertThat(all.get(0).getName()).isEqualTo("에어풋살파크2");
        assertThat(all.get(0).getLocation()).isEqualTo("부산광역시 강서구 강동동 1592");
        assertThat(all.get(1).getName()).isEqualTo("에어풋살파크1");
        assertThat(all.get(1).getLocation()).isEqualTo("부산광역시 강서구 강동동 1592");
    }
}

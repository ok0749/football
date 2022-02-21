package com.example.football.service.stadiums;

import com.example.football.domain.stadiums.StadiumsRepository;
import com.example.football.web.dto.StadiumsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StadiumsService {

    private final StadiumsRepository stadiumsRepository;

    @Transactional
    public Long save(StadiumsSaveRequestDto requestDto) {

        return stadiumsRepository.save(requestDto.toEntity()).getId();
    }
}

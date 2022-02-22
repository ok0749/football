package com.example.football.service.stadiums;

import com.example.football.domain.stadiums.Stadiums;
import com.example.football.domain.stadiums.StadiumsRepository;
import com.example.football.web.dto.StadiumsResponseDto;
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

    @Transactional
    public Long update(Long id, StadiumsSaveRequestDto requestDto) {

        Stadiums stadiums = stadiumsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 경기장이 없습니다. id=" + id));
        stadiums.update(requestDto.getName(), requestDto.getLocation());

        return id;
    }

    @Transactional
    public StadiumsResponseDto findById(Long id) {

        Stadiums entity = stadiumsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 경기장이 없습니다. id=" + id));

        return new StadiumsResponseDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        Stadiums stadiums = stadiumsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 경기장이 없습니다. id=" + id));

        stadiumsRepository.delete(stadiums);
    }
}

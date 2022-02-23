package com.example.football.domain.stadiums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StadiumsRepository extends JpaRepository<Stadiums, Long> {

    @Query("SELECT s FROM Stadiums s ORDER BY s.id DESC")
    List<Stadiums> findAllDesc();
}

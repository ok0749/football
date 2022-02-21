package com.example.football.domain.stadiums;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Stadiums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String location;

    @Builder
    public Stadiums(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void update(String name, String location) {
        this.name = name;
        this.location = location;
    }
}

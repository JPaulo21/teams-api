package com.api.football.teams.common;

import com.api.football.teams.domain.Team;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;

public class TeamConstants {

    public static final Team TEAM = Team.builder()
            .name("Raja Club Athletic")
            .nickname("Raja Casablanca")
            .foundationDate(LocalDate.of(1949, 3, 20))
            .stadium("Mohammed V Stadium")
            .state("Casablanca-Settat")
            .urlBadge("https://football.org/football-api/v1/teams/Raja_Casablanca.jpg")
            .enabled(TRUE)
            .build();
}

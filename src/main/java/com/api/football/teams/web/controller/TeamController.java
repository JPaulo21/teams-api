package com.api.football.teams.web.controller;

import com.api.football.teams.domain.Team;
import com.api.football.teams.domain.TeamService;
import com.api.football.teams.web.docs.TeamDocs;
import com.api.football.teams.web.dto.request.TeamRequest;
import com.api.football.teams.web.dto.response.TeamResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController implements TeamDocs {

    private final TeamService teamService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody @Valid TeamRequest teamRequest, UriComponentsBuilder ucb) {
        Team team = teamService.save(modelMapper.map(teamRequest, Team.class));
        URI location = ucb
                .path("/api/v1/teams/{id}")
                .buildAndExpand(team.getId())
                .toUri();
        return ResponseEntity.created(location).body(modelMapper.map(team, TeamResponse.class));
    }

}

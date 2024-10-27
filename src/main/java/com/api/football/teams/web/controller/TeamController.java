package com.api.football.teams.web.controller;

import com.api.football.teams.domain.Team;
import com.api.football.teams.domain.TeamService;
import com.api.football.teams.domain.file.Badge;
import com.api.football.teams.domain.file.BadgeService;
import com.api.football.teams.web.docs.TeamDocs;
import com.api.football.teams.web.dto.request.TeamRequest;
import com.api.football.teams.web.dto.response.TeamResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController implements TeamDocs {

    private final TeamService teamService;
    private final BadgeService badgeService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> createTeam(@RequestPart("badge") MultipartFile fileBadge
            , @RequestPart("team") @Valid TeamRequest teamRequestDTO
            , UriComponentsBuilder ucb) throws IOException {

        String extension = fileBadge.getOriginalFilename().substring(fileBadge.getOriginalFilename().lastIndexOf('.'));
        String filename = teamRequestDTO.nickname().toLowerCase()+extension;
        Badge badge = badgeService.save(Badge.builder()
                .filename(filename)
                .dataImage(fileBadge.getBytes())
                .build());

        URI locationBadge = ucb.cloneBuilder()
                .path("/api/v1/teams/badge/{filaname}")
                .buildAndExpand(badge.getFilename())
                .toUri();

        Team teamRequest = modelMapper.map(teamRequestDTO, Team.class);
        teamRequest.setUrlBadge(locationBadge.toString());

        // ------------------------------------

        Team teamSaved = teamService.save(modelMapper.map(teamRequest, Team.class));
        URI location = ucb.cloneBuilder()
                .path("/api/v1/teams/{id}")
                .buildAndExpand(teamSaved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}

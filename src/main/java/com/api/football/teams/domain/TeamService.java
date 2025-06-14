package com.api.football.teams.domain;

import com.api.football.teams.domain.badge.Badge;
import com.api.football.teams.domain.badge.BadgeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final BadgeService badgeService;

    @Transactional
    public Team save(Team team, MultipartFile fileBadge) {
        Badge badge = badgeService.save(fileBadge, team.getNickname());
        URI locationBadge = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/teams/badge/{filaname}")
                .buildAndExpand(badge.getFilename())
                .toUri();

        team.setUrlBadge(locationBadge.toString());
        team.setEnabled(TRUE);
        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Team id=%s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<Team> findByObjectFilter(Team team, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example<Team> teamExample = Example.of(team, exampleMatcher);
        return teamRepository.findAll(teamExample, pageable);
    }

    public void delete(Integer id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Team id=%s not found", id))
        );
        team.setEnabled(FALSE);
        teamRepository.save(team);
    }
}

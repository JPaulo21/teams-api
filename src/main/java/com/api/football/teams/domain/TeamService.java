package com.api.football.teams.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team save(Team team){
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
}

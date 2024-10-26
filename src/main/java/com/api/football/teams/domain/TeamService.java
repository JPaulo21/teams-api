package com.api.football.teams.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Team save(Team team){
        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Team id=%s not found", id))
        );
    }
}

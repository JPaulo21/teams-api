package com.api.football.teams.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    public Badge findByFilename(String filename) {
        return badgeRepository.findByFilename(filename).orElseThrow(
                () -> new RuntimeException("Badge not found")
        );
    }

    public Badge save(Badge file) {
        return badgeRepository.save(file);
    }
}

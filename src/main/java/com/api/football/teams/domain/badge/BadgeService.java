package com.api.football.teams.domain.badge;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    public Badge findByFilename(String filename) {
        return badgeRepository.findByFilename(filename).orElseThrow(
                () -> new RuntimeException("Badge not found")
        );
    }

    @Transactional
    public Badge save(MultipartFile fileBadge, String nickname) {
        String extension = fileBadge.getOriginalFilename().substring(fileBadge.getOriginalFilename().lastIndexOf('.'));
        String filename = nickname.toLowerCase() + extension;

        Badge file = null;
        try {
            file = Badge.builder()
                    .filename(filename)
                    .dataImage(fileBadge.getBytes())
                    .build();
        } catch (IOException e) {
            log.error("Arquivo corrompido | IOException: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return badgeRepository.save(file);
    }
}

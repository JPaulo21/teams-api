package com.api.football.teams.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Teams")
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundationDate;

    @Column(name = "stadium", nullable = false)
    private String stadium;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "url_badge", nullable = false)
    private String urlBadge;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

}

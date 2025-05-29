package com.api.football.teams.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.api.football.teams.common.TeamConstants.TEAM;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void createTeam_withValidData_returnsTeam(){
        Team team = teamRepository.save(TEAM);

        Team sut = testEntityManager.find(Team.class, team.getId());

        assertThat(sut).isNotNull();
    }

    @Test
    public void createTeam_withExistingData_throwsException(){
        Team team = testEntityManager.persistFlushFind(TEAM);
        testEntityManager.detach(team);
        team.setId(null);
        assertThatCode(() ->teamRepository.save(team))
            .isInstanceOf(RuntimeException.class);
    }

}

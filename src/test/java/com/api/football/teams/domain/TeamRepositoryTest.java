package com.api.football.teams.domain;

import com.api.football.teams.FakerUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void createTeam_withValidData_returnsTeam(){
         Team team = teamRepository.save(FakerUtils.entity(Team.class));

        Team sut = testEntityManager.find(Team.class, team.getId());

        assertThat(sut).isNotNull();
    }

    @Test
    public void createTeam_withExistingData_throwsException(){
        Team team = testEntityManager.persistFlushFind(FakerUtils.entity(Team.class));
        testEntityManager.detach(team);
        team.setId(null);
        assertThatCode(() ->teamRepository.save(team))
            .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource("providesInvalidTeams")
    public void createTeam_withBlankDatas_throwsException(Team team){
        assertThatThrownBy(() -> teamRepository.save(team))
            .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> providesInvalidTeams() {
        return Stream.of(
            Arguments.of(new Team()),
            Arguments.of(new Team(null, null, null, null, null, null, null)),
            Arguments.of(new Team("Sport Club do Recife", null, null, null, null, null, null)),
            Arguments.of(new Team("Sport Club do Recife", "Sport Recife", null, null, null, null, null))
        );
    }

}

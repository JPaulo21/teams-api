package com.api.football.teams.web.controller;

import com.api.football.teams.domain.Team;
import com.api.football.teams.domain.TeamService;
import com.api.football.teams.domain.badge.BadgeService;
import com.api.football.teams.utils.FakerUtils;
import com.api.football.teams.web.dto.request.TeamRequest;
import com.api.football.teams.web.dto.response.TeamResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TeamService teamService;
    @MockBean
    private BadgeService badgeService;
    @MockBean
    private ModelMapper modelMapper;


    @Test
    void createTeam_WithValidData_ReturnsCreatedAndHeaderLocation() throws Exception {
        TeamRequest teamRequest = new TeamRequest("Ibis Futebol Clube", "Ibis FC", LocalDate.of(1938, 11, 15), "Ademir Cunha", "Pernambuco");
        MockMultipartFile badge = new MockMultipartFile( "badge", "team-badge.jpg", IMAGE_JPEG_VALUE, "badge content".getBytes());
        MockMultipartFile teamJson = new MockMultipartFile("team", "", "application/json", objectMapper.writeValueAsString(teamRequest).getBytes()
        );
        Team team = FakerUtils.entity(Team.class);

        when(modelMapper.map(any(TeamRequest.class), eq(Team.class))).thenReturn(team);
        when(teamService.save(any(), any())).thenReturn(team);

        mockMvc.perform(multipart("/v1/teams")
                        .file(badge)
                        .file(teamJson)
                        .contentType(MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void getTeamById_WithValidId_ReturnsTeam() throws Exception {
        Integer teamId = 1;
        Team team = FakerUtils.entity(Team.class);
        TeamResponse teamResponse = FakerUtils.entity(TeamResponse.class);

        when(teamService.findById(teamId)).thenReturn(team);
        when(modelMapper.map(team, TeamResponse.class)).thenReturn(teamResponse);

        mockMvc.perform(get("/v1/teams/{id}", teamId)
                        .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teamResponse)));
    }

}

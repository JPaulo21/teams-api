package com.api.football.teams.web.docs;

import com.api.football.teams.web.dto.request.TeamFilterRequest;
import com.api.football.teams.web.dto.request.TeamRequest;
import com.api.football.teams.web.dto.response.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Tag(name = "Teams", description = "Endpoints for teams")
public interface TeamDocs {

    @Operation(summary = "Create a new team", description = "Send data of team for register", tags ={"Teams"})
    @ApiResponses(value =
        @ApiResponse(
            responseCode = "201", description = "Team",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamDocs.class))
        )
    )
    ResponseEntity<Void> createTeam(@Parameter(description = "Arquivo de badge para upload") MultipartFile badgeFile, TeamRequest teamRequest, UriComponentsBuilder ucb) throws IOException;

    @Operation(summary = "Get Team by id", description = "Obter dados de um club pelo id", tags ={"Teams"})
    ResponseEntity<TeamResponse> getTeamById(Integer id);

    @Operation(summary = "Get Team by attribute", description = "Obter dados de um club pelo id", tags ={"Teams"})
    ResponseEntity<Page<TeamResponse>> getTeamByFilter(TeamFilterRequest teamRequest, Pageable pageable);

    @Operation(summary = "Delete team by id", description = "Delete team by id", tags ={"Teams"})
    @ApiResponses(value = @ApiResponse(responseCode = "204 ", description = "Team"))
    ResponseEntity<Void> deleteTeam(Integer id);
}

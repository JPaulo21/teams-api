package com.api.football.teams.web.docs;

import ch.qos.logback.core.boolex.EvaluationException;
import com.api.football.teams.domain.Team;
import com.api.football.teams.web.dto.request.TeamRequest;
import com.api.football.teams.web.dto.response.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@Tag(name = "Teams", description = "Endpoints for teams")
public interface TeamDocs {

    @Operation(summary = "Create a new  team", description = "Send data of team for register", tags ={"Teams"})
    @ApiResponses(value =
        @ApiResponse(
            responseCode = "201", description = "Team",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamDocs.class))
        )
    )
    ResponseEntity<TeamResponse> createTeam(TeamRequest teamRequest, UriComponentsBuilder ucb);

}

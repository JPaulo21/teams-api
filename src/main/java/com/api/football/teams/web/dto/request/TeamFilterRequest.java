package com.api.football.teams.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record TeamFilterRequest(
        @Schema(description = "Name for team", example = "Sport Club do Recife")
        String name,
        @Schema(description = "Nickname for team", example = "Sport")
        String nickname,
        @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
        @Schema(description = "Date de fundação do clube", example = "13/05/1905", pattern = "dd/MM/yyyy")
        LocalDate foundationDate,
        @Schema(description = "Estádio que equipe manda seus jogos", example = "Ilha do Retiro")
        String stadium,
        @Schema(description = "Estado do clube", example = "Pernambuco")
        String state,
        @Schema(description = "Clubes ativos", example = "true")
        Boolean enabled
) {
}

package com.api.football.teams.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.Base64;

public record TeamRequest(
        @NotBlank
        @Schema(description = "Name for team",example = "Sport Club do Recife")
        String name,
        @NotBlank
        @Schema(description = "Name for team",example = "Sport")
        String nickname,
        @Past
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
        @Schema(description = "Date de fundação do clube", example = "13/05/1905", pattern = "dd/MM/yyyy")
        LocalDate foundationDate,
        @NotBlank
        @Schema(description = "Estádio que equipe manda seus jogos", example = "Ilha do Retiro")
        String stadium,
        @NotBlank
        @Schema(description = "Estado do clube", example = "Pernambuco")
        String state
) {
}

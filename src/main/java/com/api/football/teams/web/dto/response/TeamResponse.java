package com.api.football.teams.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TeamResponse{

        @NotBlank
        private String name;
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate foundationDate;
        @NotBlank
        private String nickname;
        @NotBlank
        private String stadium;
        @NotBlank
        @Schema(description = "Estado do clube", example = "Pernambuco")
        private String state;
        @URL
        @Schema(description = "Url para o escudo do clube", example = "https://image.discovery.indazn.com/ca/v3/ca/none/db5638ed-c406-480c-bac9-418539199f18/fill/center/top/none/85/2400/1350/webp/image")
        private String urlBadge;
}

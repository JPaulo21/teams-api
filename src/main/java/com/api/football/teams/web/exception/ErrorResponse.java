package com.api.football.teams.web.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        Integer code,
        HttpStatus httpStatus,
        String message) {
}

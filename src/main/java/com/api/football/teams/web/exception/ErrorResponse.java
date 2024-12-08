package com.api.football.teams.web.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        int StatusCode,
        HttpStatus httpStatus,
        String message) {
}

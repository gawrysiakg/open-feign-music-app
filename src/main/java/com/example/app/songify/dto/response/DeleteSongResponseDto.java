package com.example.app.songify.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus httpStatus) {
}

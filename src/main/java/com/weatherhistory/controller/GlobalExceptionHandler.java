package com.weatherhistory.controller;

import com.weatherhistory.ApiRequestFailedException;
import com.weatherhistory.CityNotFoundException;
import com.weatherhistory.dto.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiRequestFailedException.class)
    public ResponseEntity<ErrorMessage> handleApiException(ApiRequestFailedException e) {
        logger.error("Failed to connect to external API", e);

        return new ResponseEntity<>(new ErrorMessage("Failed to connect to API"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleApiException(CityNotFoundException e) {
        logger.error("Failed find a city", e);

        return new ResponseEntity<>(new ErrorMessage("Failed to find a city"), HttpStatus.NOT_FOUND);
    }
}

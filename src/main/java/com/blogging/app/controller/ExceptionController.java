package com.blogging.app.controller;

import com.blogging.app.service.UserService;
import com.blogging.app.vo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({UserService.UserNotFoundException.class,
            UserService.InvalidCredentials.class})
    ResponseEntity<ErrorResponse> handleUserExceptions(Exception e) {
        ErrorResponse response;
        HttpStatus status;

        if (e instanceof UserService.UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            response = ErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
        else if (e instanceof UserService.InvalidCredentials) {
            status = HttpStatus.BAD_REQUEST;
            response = ErrorResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
        else{
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = ErrorResponse.builder()
                    .message("Some thing went wrong")
                    .build();
        }
        return ResponseEntity.status(status).body(response);
    }
}

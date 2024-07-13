package com.blogging.app.vo;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponse {
    String message;

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message +
                '}';
    }
}

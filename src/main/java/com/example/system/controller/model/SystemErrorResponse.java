package com.example.system.controller.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.io.Serializable;

@Slf4j
@Builder
public class SystemErrorResponse implements ErrorResponse {
    private String errorCode;
    private String errorMessage;

    @Override
    public HttpStatusCode getStatusCode() {
        return null;
    }

    @Override
    public ProblemDetail getBody() {
        return null;
    }
}

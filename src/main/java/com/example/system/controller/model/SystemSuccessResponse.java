package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Builder
public class SystemErrorResponse {
    @JsonProperty("timestamp")
    private ZonedDateTime exceptionOccurrenceTime;
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private String error;
    @JsonProperty("message")
    private String message;
    @JsonProperty("path")
    private String path;
//    @JsonProperty("errorDetail")
//    private ErrorDetail errorDetail;
}

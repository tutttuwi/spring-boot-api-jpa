package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Setter
public class SystemSuccessResponse<R> {
    @JsonProperty("data")
    private R data;

    @JsonProperty("timestamp")
    private ZonedDateTime timestamp;
    @JsonProperty("status")
    private int status;

    public SystemSuccessResponse(HttpStatus status, R data) {
        this.timestamp = ZonedDateTime.now();
        this.status = status.value();
        this.data = data;
    }
}

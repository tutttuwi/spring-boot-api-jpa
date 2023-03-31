package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * システムレスポンス 処理失敗.<br/>
 */
@Getter
public class SystemErrorResponse extends SystemAbstractResponse {
    @JsonProperty("error")
    private String error;
    @JsonProperty("message")
    private String message;

    public SystemErrorResponse(HttpStatus status, Exception ex) {
        this.setTimestamp(ZonedDateTime.now());
        this.setStatus(status.value());
        this.error = status.getReasonPhrase();
        this.message = ex.getMessage();
    }
}

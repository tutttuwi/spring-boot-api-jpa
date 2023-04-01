package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * システムレスポンス 処理失敗.<br/>
 */
@Getter
public class SystemErrorResponse extends SystemAbstractResponse {
  @JsonProperty("error")
  private final String error;
  @JsonProperty("message")
  private final String message;

  public SystemErrorResponse(HttpStatus status, Exception ex) {
    this.setTimestamp(ZonedDateTime.now());
    this.setStatus(status.value());
    this.error = status.getReasonPhrase();
    this.message = ex.getMessage();
  }
}

package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * システムレスポンス 処理成功.<br/>
 */
@Setter
public class SystemSuccessResponse extends SystemAbstractResponse {
  @JsonProperty("data")
  private Object data;

  public SystemSuccessResponse(HttpStatus status, Object data) {
    this.setTimestamp(ZonedDateTime.now());
    this.setStatus(status.value());
    this.data = data;
  }
}

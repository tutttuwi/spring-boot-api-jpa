package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Setter;

/**
 * システム共通レスポンスオブジェクト.<br/>
 */
@Setter
public class SystemAbstractResponse {
  // 共通パラメータ定義

  @JsonProperty("timestamp")
  private ZonedDateTime timestamp;
  @JsonProperty("status")
  private int status;

}

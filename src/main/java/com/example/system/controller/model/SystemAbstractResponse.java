package com.example.system.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.time.ZonedDateTime;

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

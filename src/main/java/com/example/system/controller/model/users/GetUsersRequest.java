package com.example.system.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "ユーザ情報検索リクエスト")
public class GetUsersRequest implements Serializable {

    @Schema(description = "userId", example = "1")
    private Long userId;
    @Schema(description = "user's first name", example = "tom")
    private String fstName;
    @Schema(description = "user's last name", example = "brown")
    private String lstName;
    @Schema(description = "user's birth day", example = "1990-01-01")
    private LocalDate birthDt;

}

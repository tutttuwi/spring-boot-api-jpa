package com.example.system.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "ユーザ情報登録リクエスト")
public class PostUsersRequest implements Serializable {

    @NotBlank
    @Schema(description = "user's first name", example = "tom")
    private String fstName;
    @NotBlank
    @Schema(description = "user's last name", example = "brown")
    private String lstName;
    @Schema(description = "user's birth day", example = "1990-01-01")
    private LocalDate birthDt;

}

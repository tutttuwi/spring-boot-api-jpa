package com.example.system.controller.model.users;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Schema(description = "ユーザ情報更新リクエスト")
public class PutUsersRequest implements Serializable {

  @Schema(description = "user's first name", example = "tom")
  private String fstName;
  @Schema(description = "user's last name", example = "brown")
  private String lstName;
  @Schema(description = "user's birth day", example = "1990-01-01")
  private LocalDate birthDt;

}

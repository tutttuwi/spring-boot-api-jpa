package com.example.system.controller.model.users;

import com.example.system.constant.ValidateConst;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Component
@Schema(description = "ユーザ情報登録リクエスト")
public class PostUsersRequest implements Serializable {

    @NotBlank
    @Size(max = ValidateConst.FST_NAME_LEN_MAX, message = "{input.min.max.size}")
    @Schema(description = "user's first name", example = "tom")
    private String fstName;

    @NotBlank
    @Size(max = ValidateConst.LST_NAME_LEN_MAX, message = "{input.min.max.size}")
    @Schema(description = "user's last name", example = "brown")
    private String lstName;

    @Schema(description = "user's birth day", example = "1990-01-01")
    private LocalDate birthDt;

}

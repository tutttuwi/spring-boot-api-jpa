package com.example.system.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String userId;
    private String fstName;
    private String lstName;
    private LocalDateTime birthDt;
}

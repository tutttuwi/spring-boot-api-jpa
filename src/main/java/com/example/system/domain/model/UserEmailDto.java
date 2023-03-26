package com.example.system.domain.model;

import com.example.system.controller.model.users.PostUsersRequest;
import com.example.system.controller.model.users.PutUsersRequest;
import com.example.system.domain.model.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long userId;
    private String fstName;
    private String lstName;
    private LocalDate birthDt;


    public static UserDto of(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setFstName(user.getFstName());
        dto.setLstName(user.getLstName());
        dto.setBirthDt(user.getBirthDt());
        dto.set
        user.getUserEmail();
        return dto;
    }

    public static UserDto of(PostUsersRequest usersRequest) {
        UserDto dto = new UserDto();
        dto.setFstName(usersRequest.getFstName());
        dto.setLstName(usersRequest.getLstName());
        dto.setBirthDt(usersRequest.getBirthDt());
        return dto;
    }

    public static UserDto of(PutUsersRequest usersRequest) {
        UserDto dto = new UserDto();
        dto.setFstName(usersRequest.getFstName());
        dto.setLstName(usersRequest.getLstName());
        dto.setBirthDt(usersRequest.getBirthDt());
        return dto;
    }


    public static User toUser(UserDto userDto) {
        User entity = new User();
        entity.setUserId(userDto.getUserId());
        entity.setFstName(userDto.getFstName());
        entity.setLstName(userDto.getLstName());
        entity.setBirthDt(userDto.getBirthDt());
        return entity;
    }
}

package com.example.system.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class UserDto {
  private Long userId;
  private String fstName;
  private String lstName;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDt;

  private List<UserEmailDto> userEmail;

  // modelからrepositoryに依存させない
//    public static UserDto of(User user) {
//        UserDto dto = new UserDto();
//        dto.setUserId(user.getUserId());
//        dto.setFstName(user.getFstName());
//        dto.setLstName(user.getLstName());
//        dto.setBirthDt(user.getBirthDt());
//        List<UserEmail> userEmailList = user.getUserEmailList();
//        List<UserEmailDto> userEmailDtoList = userEmailList.stream().map(UserEmailDto::of).collect(Collectors.toList());
//        dto.setUserEmail(userEmailDtoList);
//        return dto;
//    }
//
//    public static UserDto of(PostUsersRequest usersRequest) {
//        UserDto dto = new UserDto();
//        dto.setFstName(usersRequest.getFstName());
//        dto.setLstName(usersRequest.getLstName());
//        dto.setBirthDt(usersRequest.getBirthDt());
//        return dto;
//    }
//
//    public static UserDto of(PutUsersRequest usersRequest) {
//        UserDto dto = new UserDto();
//        dto.setFstName(usersRequest.getFstName());
//        dto.setLstName(usersRequest.getLstName());
//        dto.setBirthDt(usersRequest.getBirthDt());
//        return dto;
//    }
//
//
//    public static User toUser(UserDto userDto) {
//        User entity = new User();
//        entity.setUserId(userDto.getUserId());
//        entity.setFstName(userDto.getFstName());
//        entity.setLstName(userDto.getLstName());
//        entity.setBirthDt(userDto.getBirthDt());
//        return entity;
//    }
}

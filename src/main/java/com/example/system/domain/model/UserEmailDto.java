package com.example.system.domain.model;

import lombok.Data;

@Data
public class UserEmailDto {

  private int branch;
  private char emailType;
  private String emailAddr;

//    public static UserEmailDto of(UserEmail userEmail) {
//        UserEmailDto dto = new UserEmailDto();
//        dto.setBranch(userEmail.getBranch());
//        dto.setEmailType(userEmail.getEmailType());
//        dto.setEmailAddr(userEmail.getEmailAddr());
//        return dto;
//    }
}

package com.example.system.domain.service;

import com.example.system.domain.model.UserDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public interface UserService {

  List<UserDto> searchUserList(UserDto userDto);

  UserDto searchUser(Long userId);

  UserDto createUser(UserDto userDto);

  UserDto updateUser(UserDto userDto);

  void deleteUser(Long userId);
}

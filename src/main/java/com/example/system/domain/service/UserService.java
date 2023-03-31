package com.example.system.domain.service;

import com.example.system.domain.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface UserService {

    public List<UserDto> searchUserList(UserDto userDto);
    public UserDto searchUser(Long userId);
    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    public void deleteUser(Long userId);
}

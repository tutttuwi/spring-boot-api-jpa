package com.example.system.domain.service.impl;

import com.example.system.domain.model.UserDto;
import com.example.system.domain.model.entity.User;
import com.example.system.domain.repository.UserRepository;
import com.example.system.domain.service.UserService;
import com.example.system.exception.NoDataFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  UserRepository userRepository;
  ModelMapper mapper;

  @Autowired
  UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public List<UserDto> searchUserList(UserDto userDto) {
    User tarUser = mapper.map(userDto, User.class);
//        List<User> userList = userRepository.findAll();
    List<User> userList = userRepository.findAllUserList(tarUser);
//        List<UserDto> userDtoList = userList.stream().map(UserDto::of).collect(Collectors.toList());
//        List<User> userList = userRepository.findAllUsers();

//        List<UserDto> userDtoList = userList.stream().map(UserDto::of).collect(Collectors.toList());
    List<UserDto> userDtoList =
        userList.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    log.info(userList.toString());
//        log.info(ToStringBuilder.reflectionToString(userList));
    for (User user : userList) {
      log.info(ToStringBuilder.reflectionToString(user));
    }
    return userDtoList;
  }

  @Override
  public UserDto searchUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new NoDataFoundException(String.valueOf(userId)));
    UserDto userDto = mapper.map(user, UserDto.class);
//        UserDto userDto = UserDto.of(user);
    return userDto;
  }

  @Override
  public UserDto createUser(UserDto userDto) {
//        User savedEntity = userRepository.save(UserDto.toUser(userDto));
    User savedEntity = userRepository.save(mapper.map(userDto, User.class));
    return mapper.map(savedEntity, UserDto.class);
  }

  @Override
  public UserDto updateUser(UserDto userDto) {
//        User savedEntity = userRepository.save(UserDto.toUser(userDto));
    User savedEntity = userRepository.save(mapper.map(userDto, User.class));
    return mapper.map(savedEntity, UserDto.class);
  }

  @Override
  public void deleteUser(Long userId) {
    userRepository.deleteById(userId);
  }

}

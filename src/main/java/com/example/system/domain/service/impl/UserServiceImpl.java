package com.example.system.domain.service.impl;

import static com.example.system.config.JpaSystemDbRefConfig.SYSTEM_DB_REF_TRANSACTION_MANAGER;

import com.example.system.domain.model.UserDto;
import com.example.system.domain.model.entity.User;
import com.example.system.domain.repository.systemdb.UserRepository;
import com.example.system.domain.repository.systemdbref.UserRepositoryRef;
import com.example.system.domain.service.UserService;
import com.example.system.exception.NoDataFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  UserRepository userRepository;
  UserRepositoryRef userRepositoryRef;
  ModelMapper mapper;

  @Autowired
  UserServiceImpl(UserRepository userRepository, ModelMapper mapper,
                  UserRepositoryRef userRepositoryRef) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.userRepositoryRef = userRepositoryRef;
  }

  @Override
  @Transactional(readOnly = true, transactionManager = SYSTEM_DB_REF_TRANSACTION_MANAGER)
  public List<UserDto> searchUserList(UserDto userDto) {
    User tarUser = mapper.map(userDto, User.class);
    List<User> userList = userRepositoryRef.findAllUserList(tarUser);
    List<UserDto> userDtoList =
        userList.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    log.info(userList.toString());
    for (User user : userList) {
      log.info(ToStringBuilder.reflectionToString(user));
    }
    return userDtoList;
  }

  @Override
  @Transactional(readOnly = true, transactionManager = SYSTEM_DB_REF_TRANSACTION_MANAGER)
  public UserDto searchUser(Long userId) {
    User user = userRepositoryRef.findById(userId)
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

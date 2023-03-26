package com.example.system.domain.service;

import com.example.system.domain.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
interface UserService {

    public List<UserDto> searchUserList();
}

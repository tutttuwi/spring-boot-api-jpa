package com.example.system.domain.repository;

import com.example.system.domain.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepositoryCustom {
    List<User> findAllUsers();
}

package com.example.system.domain.repository;

import com.example.system.domain.model.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryCustom {
  List<User> findAllUsers();
}

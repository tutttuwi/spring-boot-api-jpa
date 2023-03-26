package com.example.system.controller.impl;

import com.example.system.controller.UserController;
import com.example.system.controller.model.GetUsersRequest;
import com.example.system.domain.service.UserService;
import com.example.system.domain.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserControllerImpl extends AbstractBaseController implements UserController {

    @Autowired
    UserService userService;

    /**
     * コンストラクター
     *
     * @param userService
     */
    UserControllerImpl(UserServiceImpl userService) {
        userService = userService;
    }

    @Override
    public Object getUsers(GetUsersRequest userListReq, BindingResult result) throws Throwable {
        return "OK!!a!";
    }

    @Override
    public Object getUser(Long userId) throws Throwable {
        log.info(String.valueOf(userId));
        return null;
    }


}

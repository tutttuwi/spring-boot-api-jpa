package com.example.system.controller.impl;

import com.example.system.controller.UserController;
import com.example.system.controller.model.SystemAbstractResponse;
import com.example.system.controller.model.SystemSuccessResponse;
import com.example.system.controller.model.users.GetUsersRequest;
import com.example.system.controller.model.users.PostUsersRequest;
import com.example.system.controller.model.users.PutUsersRequest;
import com.example.system.domain.model.UserDto;
import com.example.system.domain.service.UserService;
import com.example.system.domain.service.impl.UserServiceImpl;
import com.example.system.exception.ValidationErrorException;
import com.example.system.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserControllerImpl extends AbstractBaseController implements UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    /**
     * コンストラクター
     *
     * @param userService
     * @param mapper
     */
    @Autowired
    public UserControllerImpl(UserServiceImpl userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<SystemAbstractResponse> getUsers(GetUsersRequest userListReq) throws Throwable {
        UserDto userDto = mapper.map(Optional.ofNullable(userListReq).orElse(new GetUsersRequest()), UserDto.class);
//        log.info("aaa");
        List<UserDto> userDtoResult = userService.searchUserList(userDto);
        return ResponseEntity.ok().body(new SystemSuccessResponse(HttpStatus.OK, userDtoResult));
//        return new SystemSuccessResponse(HttpStatus.OK, userDtoResult);
    }

    @Override
    public ResponseEntity<SystemAbstractResponse> getUser(Long userId) throws Throwable {
        log.info(String.valueOf(userId));
        UserDto userDto = userService.searchUser(userId);
        return ResponseEntity.ok().body(new SystemSuccessResponse(HttpStatus.OK, userDto));
//        return new SystemSuccessResponse(HttpStatus.OK, userDto);
    }

    @Override
    public ResponseEntity<SystemAbstractResponse> postUser(PostUsersRequest usersReq, BindingResult result) throws Throwable {
        if (result.hasErrors()) {
            throw new ValidationErrorException(result.getFieldErrors());
        }
//        List<String> errorList = retrieveErrors(result);
//        if (!errorList.isEmpty()) {
//            return new SystemErrorResponse(HttpStatus.BAD_REQUEST, new Exception(errorList.toString()));
//        }
        UserDto createdDto = userService.createUser(mapper.map(usersReq, UserDto.class));
        String uri = UriUtils.getUriPath("/Users/" + createdDto.getUserId());
        return ResponseEntity.created(URI.create(uri)).body(new SystemSuccessResponse(HttpStatus.CREATED, createdDto));
//        return new SystemSuccessResponse(HttpStatus.CREATED, createdDto);
    }

    @Override
    public ResponseEntity<SystemAbstractResponse> putUser(Long userId, PutUsersRequest usersReq, BindingResult result) throws Throwable {
        UserDto searchedDto = userService.searchUser(userId);
//        UserDto createDto = UserDto.of(usersReq);
        UserDto updateDto = userService.createUser(mapper.map(usersReq, UserDto.class));
        updateDto.setUserId(searchedDto.getUserId());
        UserDto updatedDto = userService.updateUser(updateDto);
        return ResponseEntity.ok().body(new SystemSuccessResponse(HttpStatus.OK, updatedDto));
//        return new SystemSuccessResponse(HttpStatus.OK, updatedDto);
    }

    @Override
    public ResponseEntity<SystemAbstractResponse> deleteUser(Long userId) throws Throwable {
        userService.deleteUser(userId);
        // 物理削除ならNO_CONTENT(204)でコンテンツ返却しない、論理削除ならOK(200)でコンテンツ返却
        return ResponseEntity.noContent().build();
    }

}

package com.example.system.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.system.SystemAbstractTest;
import com.example.system.controller.impl.UserControllerImpl;
import com.example.system.domain.model.UserDto;
import com.example.system.domain.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Slf4j
@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
@DisplayName("ユーザ情報コントローラ")
public class UserControllerTest extends SystemAbstractTest {

  /**
   * ControllerMock用
   */
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;

  @InjectMocks
  private UserControllerImpl controller;

  @DisplayName("ユーザ情報取得")
  @Test
  void getUser() throws Exception {

    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    userDto.setFstName("fstName");
    userDto.setLstName("lstName");
    userDto.setBirthDt(LocalDate.now());
    // Mock
    Mockito.when(userService.searchUser(1L)).thenReturn(userDto);
    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users/1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

  }

  @DisplayName("ユーザ情報リスト取得")
  @Test
  void getUsers() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    userDto.setFstName("fstName");
    userDto.setLstName("lstName");
    userDto.setBirthDt(LocalDate.now());
    // Mock
    Mockito.when(userService.searchUserList(userDto)).thenReturn(Arrays.asList(userDto));

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

  }

  @DisplayName("ユーザ情報登録")
  @Test
  void postUser() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    userDto.setFstName("fstName");
    userDto.setLstName("lstName");
    userDto.setBirthDt(LocalDate.now());
    // Mock
    Mockito.when(userService.updateUser(userDto)).thenReturn(userDto);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(userDto);

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(
            post("/users/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json));
    res.andExpect(status().isOk());
    log.info(res.toString());

  }

}


package com.example.system.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.system.SystemAbstractTest;
import com.example.system.controller.impl.UserControllerImpl;
import com.example.system.domain.model.UserDto;
import com.example.system.domain.model.UserEmailDto;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ユーザ情報コントローラ")
public class UserControllerTest extends SystemAbstractTest {

  /**
   * ControllerMock用
   */
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl mockUserService;

  @InjectMocks
  private UserControllerImpl testController;

  @DisplayName("ユーザ情報取得")
  @Test
  void getUser() throws Exception {

    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    userDto.setFstName("fstName");
    userDto.setLstName("lstName");
    userDto.setBirthDt(LocalDate.now());
    // Mock
    Mockito.when(mockUserService.searchUser(1L)).thenReturn(userDto);
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
    UserEmailDto userEmailDto = new UserEmailDto();
    userEmailDto.setBranch(1);
    userEmailDto.setEmailAddr("test@example.com");
    userEmailDto.setEmailType('1');
    UserEmailDto userEmailDto2 = new UserEmailDto();
    userEmailDto2.setBranch(2);
    userEmailDto2.setEmailAddr("test2@example.com");
    userEmailDto2.setEmailType('1');
    userDto.setUserEmail(Arrays.asList(userEmailDto, userEmailDto2));
    // Mock
    Mockito.when(mockUserService.searchUserList(userDto)).thenReturn(Arrays.asList(userDto));

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
    UserEmailDto userEmailDto = new UserEmailDto();
    userEmailDto.setBranch(1);
    userEmailDto.setEmailAddr("test@example.com");
    userEmailDto.setEmailType('1');
    UserEmailDto userEmailDto2 = new UserEmailDto();
    userEmailDto2.setBranch(2);
    userEmailDto2.setEmailAddr("test2@example.com");
    userEmailDto2.setEmailType('1');
    userDto.setUserEmail(Arrays.asList(userEmailDto, userEmailDto2));
    // Mock
    Mockito.when(mockUserService.createUser(any())).thenReturn(userDto);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(userDto);

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(
            post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(json));
    res.andExpect(status().isCreated());
    log.info(res.toString());

  }


  @DisplayName("ユーザ情報登録 リクエスト不正")
  @Test
  void postUserInvalidRequest() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setUserId(1L);
    userDto.setFstName(null);
    userDto.setLstName("");
    userDto.setBirthDt(LocalDate.now());
    UserEmailDto userEmailDto = new UserEmailDto();
    userEmailDto.setBranch(1);
    userEmailDto.setEmailAddr("test@example.com");
    userEmailDto.setEmailType('1');
    userDto.setUserEmail(Arrays.asList(userEmailDto));
    // Mock
    Mockito.when(mockUserService.createUser(any())).thenReturn(userDto);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(userDto);

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(
            post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(json));
    res.andExpect(status().is4xxClientError());
    log.info(res.toString());

  }

  // TODO: 他メソッドも同様の形で実装

}


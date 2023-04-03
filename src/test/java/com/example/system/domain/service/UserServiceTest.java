package com.example.system.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.system.SystemAbstractTest;
import com.example.system.domain.model.UserDto;
import com.example.system.domain.model.UserEmailDto;
import com.example.system.domain.model.entity.User;
import com.example.system.domain.model.entity.UserEmail;
import com.example.system.domain.repository.systemdb.UserRepository;
import com.example.system.domain.repository.systemdbref.UserRepositoryRef;
import com.example.system.domain.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
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
@DisplayName("ユーザサービス")
public class UserServiceTest extends SystemAbstractTest {

  /**
   * ControllerMock用
   */
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository mockUserRepository;

  @MockBean
  private UserRepositoryRef mockUserRepositoryRef;

  @InjectMocks
  private UserServiceImpl testService;

  @DisplayName("ユーザ情報取得")
  @Test
  void searchUser() throws Exception {

    User user = new User();
    user.setUserId(1L);
    user.setFstName("fstName");
    user.setLstName("lstName");
    user.setBirthDt(LocalDate.now());
    // Mock
    Mockito.when(mockUserRepositoryRef.findById(any())).thenReturn(Optional.of(user));
    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users/1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

  }

  @DisplayName("ユーザ情報リスト取得")
  @Test
  void searchUserList() throws Exception {
    User user = new User();
    user.setUserId(1L);
    user.setFstName("fstName");
    user.setLstName("lstName");
    user.setBirthDt(LocalDate.now());
    UserEmail userEmail = new UserEmail();
    userEmail.setBranch(1);
    userEmail.setEmailAddr("test@example.com");
    userEmail.setEmailType('1');
    UserEmail userEmail2 = new UserEmail();
    userEmail2.setBranch(2);
    userEmail2.setEmailAddr("test2@example.com");
    userEmail2.setEmailType('2');
    user.setUserEmailList(Arrays.asList(userEmail, userEmail2));

    // Mock
    Mockito.when(mockUserRepositoryRef.findAllUserList(any())).thenReturn(Arrays.asList(user));

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

  }

  @DisplayName("ユーザ情報登録")
  @Test
  void createUser() throws Exception {
    // UserDto
    UserDto userDto = new UserDto();
    userDto.setFstName("fstName");
    userDto.setLstName("lstName");
    userDto.setBirthDt(LocalDate.now());
    UserEmailDto userEmailDto = new UserEmailDto();
    userEmailDto.setBranch(1);
    userEmailDto.setEmailAddr("test@example.com");
    userEmailDto.setEmailType('1');
    userDto.setUserEmail(Arrays.asList(userEmailDto));
    // User Entity
    User user = new User();
    user.setUserId(1L);
    user.setFstName("fstName");
    user.setLstName("lstName");
    user.setBirthDt(LocalDate.now());
    UserEmail userEmail = new UserEmail();
    userEmail.setBranch(1);
    userEmail.setEmailAddr("test@example.com");
    userEmail.setEmailType('1');
    UserEmail userEmail2 = new UserEmail();
    userEmail2.setBranch(2);
    userEmail2.setEmailAddr("test2@example.com");
    userEmail2.setEmailType('2');
    user.setUserEmailList(Arrays.asList(userEmail, userEmail2));
    // Mock
    Mockito.when(mockUserRepository.save(any())).thenReturn(user);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(userDto);

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(
            post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(json));
    res.andExpect(status().isCreated());
    log.info(res.toString());

  }

  // TODO: 他メソッドも同様の形で実装

}


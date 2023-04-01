package com.example.system.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.system.controller.impl.UserControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(UserControllerImpl.class)
@TestPropertySource(locations = "/application-test.yml")
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * 前処理(各テストケースを実行する前に行われる処理)
   */
  @BeforeEach
  public void setup() {
  }

  @DisplayName("ユーザ情報取得")
  @Test
  void getUser() throws Exception {
    ResultActions res =
        this.mockMvc.perform(get("/users/1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());
  }

  @DisplayName("ユーザ情報リスト取得")
  @Test
  void getUsers() throws Exception {
    ResultActions res =
        this.mockMvc.perform(get("/users").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());
  }

}

package com.example.system.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.system.SystemAbstractTest;
import com.utils.DbUnitInputData;
import com.utils.DbUnitOutputData;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@DisplayName("ユーザ情報コントローラ")
public class UserControllerTest extends SystemAbstractTest {

  /**
   * ControllerMock用
   */
  @Autowired
  private MockMvc mockMvc;

  @DisplayName("ユーザ情報取得")
  @Test
//  @DatabaseSetup(value = { // MEMO: アノテーションでの入出力情報定義に限界があるためコメントアウト
//      "UserControllerTest_prepare_ユーザ情報検索.xml"}, connection = SYSTEM_DB_REF_DATASOURCE)
//  @ExpectedDatabase(
//      value = "UserControllerTest_expected_ユーザ情報検索.xml",
//      connection = SYSTEM_DB_REF_DATASOURCE,
//      assertionMode = DatabaseAssertionMode.NON_STRICT // デフォルトでは全てのテーブルが検査されてしまうためxmlに記述したテーブルのみ検査するためNON_STRICTに設定
//  )
  void getUser() throws Exception {
    // データ設定
    List<DbUnitInputData> dbUnitInputDataList = Arrays.asList(
        new DbUnitInputData(systemdbPublicConnection, "UserControllerTest_prepare_ユーザ情報検索.xml"),
        new DbUnitInputData(systemdbrefPublicConnection,
            "UserControllerTest_prepare_ユーザ情報検索.xml")
    );
    List<DbUnitOutputData> dbUnitOutputDataList = Arrays.asList(
        new DbUnitOutputData(systemdbDataSet, systemdbTableList,
            "UserControllerTest_expected_ユーザ情報検索.xml"),
        new DbUnitOutputData(systemdbDataSet, systemdbrefTableList,
            "UserControllerTest_expected_ユーザ情報検索.xml")
    );
    // テストデータ設定
    prepareTestData(dbUnitInputDataList);

    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users/1").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

    // データチェック
    verifyData(dbUnitOutputDataList);
  }

  @DisplayName("ユーザ情報リスト取得")
  @Test
  void getUsers() throws Exception {
    // データ設定
    List<DbUnitInputData> dbUnitInputDataList = Arrays.asList(
        new DbUnitInputData(systemdbPublicConnection, "UserControllerTest_prepare_ユーザ情報検索.xml"),
        new DbUnitInputData(systemdbrefPublicConnection,
            "UserControllerTest_prepare_ユーザ情報検索.xml")
    );
    List<DbUnitOutputData> dbUnitOutputDataList = Arrays.asList(
        new DbUnitOutputData(systemdbDataSet, systemdbTableList,
            "UserControllerTest_expected_ユーザ情報検索.xml"),
        new DbUnitOutputData(systemdbDataSet, systemdbrefTableList,
            "UserControllerTest_expected_ユーザ情報検索.xml")
    );
    // テストデータ設定
    prepareTestData(dbUnitInputDataList);
    // テスト実施
    ResultActions res =
        this.mockMvc.perform(get("/users").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    res.andExpect(status().isOk()).andExpect(jsonPath("data").exists());

    // データチェック
    verifyData(dbUnitOutputDataList);

  }

}


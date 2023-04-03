package com.example.system;

import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * テスト共通処理.<br/>
 * 各テストクラスで継承して利用することを想定
 * DBリソース定義などは共通利用して各クラスで再定義しない
 * 上書きが必要なクラスだけクラス内で上書く形とする
 */
@Slf4j
@ActiveProfiles("test")
public abstract class SystemAbstractTest {

  /**
   * 前処理(全テストケースを実行する前に１回行われる処理)
   */
  @BeforeAll
  protected static void setupBeforeAll(TestInfo testInfo) {
    log.info("==================================================");
    log.info(
        "[START] {} {}",
        testInfo.getTestClass().get().getSimpleName()
        , testInfo.getDisplayName());
    log.info("==================================================");
  }

  /**
   * 前処理(全テストケースを実行した後に１回行われる処理)
   */
  @AfterAll
  protected static void setupAfterAll(TestInfo testInfo) {
    log.info("==================================================");
    log.info(
        "[END] {} {}",
        testInfo.getTestClass().get().getSimpleName()
        , testInfo.getDisplayName());
    log.info("==================================================");
  }

  /**
   * 前処理(各テストケースを実行する前に行われる処理)
   */
  @BeforeEach
  protected void setupBeforeEach(TestInfo testInfo) throws SQLException {
    log.info("--------------------------------------------------");
    log.info(
        "[START] {} {} {}",
        testInfo.getTestClass().get().getSimpleName()
        , testInfo.getTestMethod().get().getName()
        , testInfo.getDisplayName());
    log.info("--------------------------------------------------");
  }

  /**
   * 前処理(各テストケースを実行した後に行われる処理)
   */
  @AfterEach
  protected void setupAfterEach(TestInfo testInfo) {
    log.info("--------------------------------------------------");
    log.info(
        "[END] {} {} {}",
        testInfo.getTestClass().get().getSimpleName()
        , testInfo.getTestMethod().get().getName()
        , testInfo.getDisplayName());
    log.info("--------------------------------------------------");
  }

}

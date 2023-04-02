package com.example.system;

import static com.example.system.SystemIntegrationTestConfiguration.SYSTEM_DB_PUBLIC_CONNECTION_TEST;
import static com.example.system.SystemIntegrationTestConfiguration.SYSTEM_DB_REF_PUBLIC_CONNECTION_TEST;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.utils.DbConstant;
import com.utils.DbUnitInputData;
import com.utils.DbUnitOutputData;
import com.utils.DbUnitUtils;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * テスト共通処理.<br/>
 * 各テストクラスで継承して利用することを想定
 * DBリソース定義などは共通利用して各クラスで再定義しない
 * 上書きが必要なクラスだけクラス内で上書く形とする
 */
//@ContextConfiguration(classes = {SystemTestConfiguration.class}) // 指定すると、アプリ側のコンポーネントを読み込んでくれないみたい。指定するなら全て指定すべき
//@WebMvcTest(UserControllerImpl.class)
@Slf4j
@SpringBootTest
@ActiveProfiles("integrationtest")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@DbUnitConfiguration(
    databaseConnection = {
        SYSTEM_DB_PUBLIC_CONNECTION_TEST,
        SYSTEM_DB_REF_PUBLIC_CONNECTION_TEST
    })
public abstract class SystemAbstractIntegrationTest {

  /**
   * DBUnitコネクション(systemdb)
   */
  @Autowired
  @Qualifier(SYSTEM_DB_PUBLIC_CONNECTION_TEST)
  protected IDatabaseConnection systemdbPublicConnection;

  /**
   * DBUnitコネクション(systemdbref)
   */
  @Autowired
  @Qualifier(SYSTEM_DB_REF_PUBLIC_CONNECTION_TEST)
  protected IDatabaseConnection systemdbrefPublicConnection;

  /**
   * DBUnit比較対象テーブル(systemdb)
   */
  protected List<String> systemdbTableList = Arrays.asList(
      DbConstant.TABLE_USER,
      DbConstant.TABLE_USER_EMAIL
  );
  /**
   * DBUnit比較対象テーブル(systemdbref)
   */
  protected List<String> systemdbrefTableList = Arrays.asList(
      DbConstant.TABLE_USER,
      DbConstant.TABLE_USER_EMAIL
  );

  /**
   * DBUnit除外カラム配列<br/>
   * 比較対象外カラムを横断定期に定義
   * 基本的に比較対象外カラムは共通の想定。
   * 例外がある場合はフィルター名を変えて個別で設定する
   */
  protected String[] excludedFilter =
      new String[] {
          "create_dt",
          "update_dt"
      };

  /**
   * DBUnitソート定義<br/>
   */
  protected Map<String, String[]> sort = new HashMap<>() {{
    // [systemdb],[systemdbref]
    put(DbConstant.TABLE_USER, new String[] {"user_id"});
    put(DbConstant.TABLE_USER_EMAIL, new String[] {"user_id", "branch"});
  }};

  /**
   * DBUnitデータセット(systemdb)
   */
  protected IDataSet systemdbDataSet;
  /**
   * DBUnitデータセット(systemdbref)
   */
  protected IDataSet systemdbrefDataSet;

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
   * DBUnitデータセット設定
   *
   * @throws SQLException
   */
  protected void setDbUnitDataSet() throws SQLException {
    this.systemdbDataSet = systemdbPublicConnection.createDataSet();
    this.systemdbrefDataSet = systemdbrefPublicConnection.createDataSet();
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
    this.setDbUnitDataSet();
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

  /**
   * テストデータ準備
   *
   * @param dbUnitInputDataList
   * @throws SQLException
   * @throws DatabaseUnitException
   */
  protected void prepareTestData(List<DbUnitInputData> dbUnitInputDataList)
      throws SQLException, DatabaseUnitException {
    for (DbUnitInputData input : dbUnitInputDataList) {
      DatabaseOperation.CLEAN_INSERT.execute(input.getCon(),
          DbUnitUtils.convertXmlToData(getClass().getResourceAsStream(input.getXml())));
    }
  }

  /**
   * データ検証
   *
   * @param dbUnitOutputDataList
   * @throws SQLException
   * @throws DatabaseUnitException
   */
  protected void verifyData(
      List<DbUnitOutputData> dbUnitOutputDataList)
      throws SQLException, DatabaseUnitException {
    for (DbUnitOutputData output : dbUnitOutputDataList) {
      IDataSet expectedData =
          DbUnitUtils.convertXmlToData(getClass().getResourceAsStream(output.getXml()));
      for (String t : output.getTables()) {
        ITable actualTable =
            DbUnitUtils.sortedAndExcludedColumnsTable(
                output.getDataSet().getTable(t), sort.get(t), excludedFilter);
        ITable expectedTable =
            DbUnitUtils.sortedAndExcludedColumnsTable(
                expectedData.getTable(t), sort.get(t), excludedFilter);
        Assertion.assertEquals(expectedTable, actualTable);
      }
    }
  }

}

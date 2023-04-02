package com.example.system;

import static com.example.system.config.JpaSystemDbConfig.SYSTEM_DB_CONFIG;
import static com.example.system.config.JpaSystemDbRefConfig.SYSTEM_DB_REF_CONFIG;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Testに必要なBean定義などを管理.<br/>
 */
@Configuration
public class SystemTestConfiguration {

  public static final String SYSTEM_DB_DATASOURCE_TEST = "systemDbTest";
  public static final String SYSTEM_DB_REF_DATASOURCE_TEST = "systemDbRefTest";
  public static final String SYSTEM_DB_PUBLIC_CONNECTION_TEST = "systemDbPublicConnectionTest";
  public static final String SYSTEM_DB_REF_PUBLIC_CONNECTION_TEST =
      "systemDbRefPublicConnectionTest";

  /**
   * システムDBデータソース（DbUnit用）
   *
   * @return
   */
  @ConfigurationProperties(prefix = SYSTEM_DB_CONFIG)
  @Bean(name = SYSTEM_DB_DATASOURCE_TEST)
  public DataSource dataSourceSystemdb() {
    return DataSourceBuilder.create().build();
  }

  /**
   * システムDBコネクション（DbUnit用）
   * DBUnitのコネクションをDIする前にDATATYPEを設定する
   * {@see How to replace the default data type factory? <https://www.dbunit.org/faq.html#typefactory>}
   *
   * @param dataSource
   * @return
   * @throws SQLException
   * @throws DatabaseUnitException
   */
  @Bean(name = SYSTEM_DB_PUBLIC_CONNECTION_TEST)
  public IDatabaseConnection systemdbConnection(
      @Qualifier(SYSTEM_DB_DATASOURCE_TEST) DataSource dataSource) throws
      SQLException, DatabaseUnitException {
    CustomDatabaseConnection con =
        new CustomDatabaseConnection(dataSource.getConnection(), "public");
    DatabaseConfig config = con.getConfig();
    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
        new PostgresqlDataTypeFactory()); // TODO: DB設定
    return con;
  }

  /**
   * システムDB参照データソース（DbUnit用）
   *
   * @return
   */
  @ConfigurationProperties(prefix = SYSTEM_DB_REF_CONFIG)
  @Bean(name = SYSTEM_DB_REF_DATASOURCE_TEST)
  public DataSource dataSourceSystemdbRef() {
    return DataSourceBuilder.create().build();
  }

  /**
   * システムDB参照コネクション（DbUnit用）
   * DBUnitのコネクションをDIする前にDATATYPEを設定する
   * {@see How to replace the default data type factory? <https://www.dbunit.org/faq.html#typefactory>}
   *
   * @param dataSource
   * @return
   * @throws SQLException
   * @throws DatabaseUnitException
   */
  @Bean(name = SYSTEM_DB_REF_PUBLIC_CONNECTION_TEST)
  public IDatabaseConnection systemdbrefConnection(
      @Qualifier(SYSTEM_DB_REF_DATASOURCE_TEST) DataSource dataSource) throws
      SQLException, DatabaseUnitException {
    CustomDatabaseConnection con =
        new CustomDatabaseConnection(dataSource.getConnection(), "public");
    DatabaseConfig config = con.getConfig();
    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
        new PostgresqlDataTypeFactory()); // TODO: DB設定
    return con;
  }


  /**
   * カスタムDBコネクションクラス
   */
  public class CustomDatabaseConnection extends DatabaseConnection {
    public CustomDatabaseConnection(Connection connection, String schema)
        throws DatabaseUnitException {
      super(connection, schema);
    }

    @Override
    public void close() {
      //nop
    }

    public void lastClose() throws SQLException {
      super.close();
    }
  }


  //  @ConfigurationProperties(prefix = SYSTEM_DB_CONFIG) // MEMO: 読み込めない、原因調査必要、@Valueで読み込んで回避
//  @Bean(SYSTEM_DB_DATASOURCE)
//  @Primary
//  public DataSource createDataSource() {
//    return DataSourceBuilder
//        .create()
//        .driverClassName(driverClassName)
//        .url(url)
//        .username(username)
//        .password(password)
//        .build();
//  }

}

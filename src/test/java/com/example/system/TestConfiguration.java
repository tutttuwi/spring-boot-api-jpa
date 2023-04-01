package com.example.system;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Testに必要なBean定義などを管理.<br/>
 */
@Configuration
class TestConfiguration {

   // MEMO: データソース定義など (できる限りapplication-test.yml側で定義したほうがよい)
//  @Bean(name = "dataSourceName")
//  public DataSource dataSource() {
//    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//    dataSourceBuilder.driverClassName("org.h2.Driver");
//    dataSourceBuilder.url("");
//    dataSourceBuilder.username("");
//    dataSourceBuilder.password("");
//    return dataSourceBuilder.build();
//  }
}

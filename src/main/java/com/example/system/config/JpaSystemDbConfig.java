package com.example.system.config;//package com.example.system.config;

import static com.example.system.config.JpaSystemDbConfig.SYSTEM_DB_ENTITY_MANAGER;
import static com.example.system.config.JpaSystemDbConfig.SYSTEM_DB_REPOSITORY_PACKAGE;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = {SYSTEM_DB_REPOSITORY_PACKAGE},
    entityManagerFactoryRef = SYSTEM_DB_ENTITY_MANAGER)
public class JpaSystemDbConfig {
  public static final String SYSTEM_DB_REPOSITORY_PACKAGE =
      "com.example.system.domain.repository.systemdb";
  public static final String SYSTEM_DB_DATASOURCE = "systemDb";
  //  public static final String SYSTEM_DB_CONFIG = "spring.datasource.systemdb";
  public static final String SYSTEM_DB_ENTITY_MANAGER = "systemdbEntityManager";
  public static final String SYSTEM_DB_ENTITY_PACKAGE = "com.example.system.domain.model.entity";

  // [transactionManager]の名前で定義されてないと、JPAでエラーになるため、PrimaryのTransactionManager名は[transactionManager]で定義
  public static final String SYSTEM_DB_TRANSACTION_MANAGER = "transactionManager";
  @Value("${spring.datasource.systemdb.driverClassName}")
  private String driverClassName;
  @Value("${spring.datasource.systemdb.url}")
  private String url;
  @Value("${spring.datasource.systemdb.username}")
  private String username;
  @Value("${spring.datasource.systemdb.password}")
  private String password;

  //  @ConfigurationProperties(prefix = SYSTEM_DB_CONFIG) // MEMO: 読み込めない、原因調査必要、@Valueで読み込んで回避
  @Bean(SYSTEM_DB_DATASOURCE)
  @Primary
  public DataSource createDataSource() {
    return DataSourceBuilder
        .create()
        .driverClassName(driverClassName)
        .url(url)
        .username(username)
        .password(password)
        .build();
  }

  @Bean(name = SYSTEM_DB_ENTITY_MANAGER)
  @Primary
  public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
      @Qualifier(SYSTEM_DB_DATASOURCE) DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan(SYSTEM_DB_ENTITY_PACKAGE);

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.afterPropertiesSet();

    return factory;
  }

  @Primary
  @Bean(SYSTEM_DB_TRANSACTION_MANAGER)
  public PlatformTransactionManager dbTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        jpaEntityManagerFactory(createDataSource()).getObject());
    return transactionManager;
  }
}
package com.example.system.config;//package com.example.system.config;

import static com.example.system.config.JpaSystemDbRefConfig.SYSTEM_DB_REF_ENTITY_MANAGER;
import static com.example.system.config.JpaSystemDbRefConfig.SYSTEM_DB_REF_REPOSITORY_PACKAGE;

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
@EnableJpaRepositories(basePackages = {
    SYSTEM_DB_REF_REPOSITORY_PACKAGE},
    entityManagerFactoryRef = SYSTEM_DB_REF_ENTITY_MANAGER)
public class JpaSystemDbRefConfig {

  public static final String SYSTEM_DB_REF_REPOSITORY_PACKAGE =
      "com.example.system.domain.repository.systemdbref";
  public static final String SYSTEM_DB_DATASOURCE = "systemDbRef";
  public static final String SYSTEM_DB_REF_ENTITY_MANAGER = "systemdbrefEntityManager";
  public static final String SYSTEM_DB_REF_ENTITY_PACKAGE = "com.example.system.domain.model.entity";
  public static final String SYSTEM_DB_REF_TRANSACTION_MANAGER = "transactionManagerRef";
  @Value("${spring.datasource.systemdbref.driverClassName}")
  private String driverClassName;
  @Value("${spring.datasource.systemdbref.url}")
  private String url;
  @Value("${spring.datasource.systemdbref.username}")
  private String username;
  @Value("${spring.datasource.systemdbref.password}")
  private String password;

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

  @Bean(name = SYSTEM_DB_REF_ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
      @Qualifier(SYSTEM_DB_DATASOURCE) DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource);
    factory.setPackagesToScan(SYSTEM_DB_REF_ENTITY_PACKAGE);

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.afterPropertiesSet();

    return factory;
  }

  @Bean(SYSTEM_DB_REF_TRANSACTION_MANAGER)
  public PlatformTransactionManager dbTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        jpaEntityManagerFactory(createDataSource()).getObject());
    return transactionManager;
  }

}
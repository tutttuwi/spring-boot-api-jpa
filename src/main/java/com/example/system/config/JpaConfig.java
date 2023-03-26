//package com.example.system.config;
//
//import com.querydsl.sql.H2Templates;
//import com.querydsl.sql.SQLQueryFactory;
//import com.querydsl.sql.SQLTemplates;
//import com.querydsl.sql.spring.SpringConnectionProvider;
//import com.querydsl.sql.spring.SpringExceptionTranslator;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.inject.Provider;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.util.function.Supplier;
//
//@Configuration
//public class JdbcConfig {
////    @Bean
////    public DataSource dataSource() {
////        // implementation omitted
////    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
//        return jpaTransactionManager;
//    }
//
//    @Bean
//    public com.querydsl.sql.Configuration querydslConfiguration() {
//        SQLTemplates templates = H2Templates.builder().build(); //change to your Templates
//        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
//        configuration.setExceptionTranslator(new SpringExceptionTranslator());
//        return configuration;
//    }
//
//    @Bean
//    public SQLQueryFactory queryFactory(DataSource dataSource) {
//        Provider<Connection> provider = (Provider<Connection>) new SpringConnectionProvider(dataSource);
//        return new SQLQueryFactory(querydslConfiguration(), (Supplier<Connection>) provider);
//    }
//}

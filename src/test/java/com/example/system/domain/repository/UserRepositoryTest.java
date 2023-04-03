package com.example.system.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.system.SystemAbstractTest;
import com.example.system.domain.model.entity.User;
import com.example.system.domain.repository.systemdb.UserRepository;
import java.time.LocalDate;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

// TODO: DataJpaTestでtestEntityManager.persist()メソッドを呼ぶと以下エラー発生。未解消
// Converting `org.hibernate.PersistentObjectException` to JPA `PersistenceException` : detached entity passed to persist: com.example.system.domain.model.entity.User
//@Slf4j
//@DataJpaTest(showSql = true)
//@DisplayName("ユーザリポジトリ")
////@Import(JpaSystemDbConfig.class)
//public class UserRepositoryTest extends SystemAbstractTest {
//
//  @Autowired
//  private TestEntityManager testEntityManager;
//
//  @Autowired
//  private UserRepository userRepository;
////  @Autowired
////  private UserRepositoryRef userRepositoryRef;
//
//  @DisplayName("ユーザ情報取得")
//  @Test
//  void findById() throws Exception {
//
//    // User Entity
//    User user = new User();
//    user.setUserId(1000L);
//    user.setFstName("fstName");
//    user.setLstName("lstName");
//    user.setBirthDt(LocalDate.now());
////    UserEmail userEmail = new UserEmail();
////    userEmail.setBranch(1);
////    userEmail.setEmailAddr("test@example.com");
////    userEmail.setEmailType('1');
////    UserEmail userEmail2 = new UserEmail();
////    userEmail2.setBranch(2);
////    userEmail2.setEmailAddr("test2@example.com");
////    userEmail2.setEmailType('2');
////    user.setUserEmailList(Arrays.asList(userEmail, userEmail2));
//
//    // テストデータ登録
//    testEntityManager.persist(user);
//    // テストデータ取得
//    Optional<User> findUser = userRepository.findById(1000L);
//
//    // 検証
//    assertThat(findUser).isNotEmpty();
//
//  }
//}


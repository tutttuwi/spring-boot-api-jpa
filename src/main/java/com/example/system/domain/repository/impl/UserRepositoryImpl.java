package com.example.system.domain.repository.impl;

import com.example.system.domain.model.entity.User;
import com.example.system.domain.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * UserRepository Proxy拡張<br/>
 * Entity Managerを直接操作しなければ行けないケースがあればこちらに定義する。
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
  @PersistenceContext
  private final EntityManager em;

  public List<User> findAllUsers() {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);
//        root.fetch(User_.)

//        JPAQuery q = new JPAQuery(em);
//        queryFactory.selectFrom(user).fetch();
//        QUser u = QUser.user;
    // N+1 Problem Solution
    List<User> users =
        em.createQuery("SELECT user FROM User user JOIN FETCH user.userEmailList").getResultList();
//        List<User> users = query.selectFrom(u).fetch();
//        List<User> users = query.selectFrom(user).leftJoin(userEmail).on(user.eq(userEmail.user)).fetch();
//        List<User> users = query.from(user).select(constructor(User.class, user.userId)).leftJoin(userEmail).fetch();
//        for (User user : users) {
//            System.out.println(user);
//        }


    return users;
  }
}

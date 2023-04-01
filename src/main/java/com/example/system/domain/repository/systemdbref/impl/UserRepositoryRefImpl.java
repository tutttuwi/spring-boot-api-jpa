package com.example.system.domain.repository.systemdbref.impl;

import com.example.system.domain.model.entity.User;
import com.example.system.domain.repository.systemdb.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * UserRepositoryRef Proxy拡張 実装クラス<br/>
 * Entity Managerを直接操作しなければ行けないケースがあればこちらに定義する。
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryRefImpl implements UserRepositoryCustom {
  @PersistenceContext
  private final EntityManager em;

  public List<User> findAllUsers() {
    final CriteriaBuilder builder = em.getCriteriaBuilder();
    final CriteriaQuery<User> query = builder.createQuery(User.class);
    final Root<User> root = query.from(User.class);
    List<User> users =
        em.createQuery("SELECT user FROM User user JOIN FETCH user.userEmailList").getResultList();
    return users;
  }
}

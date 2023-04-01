package com.example.system.domain.repository.systemdb;

import com.example.system.domain.model.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserRepositoryCustom Proxy拡張 インターフェース<br/>
 * JpaRepositoryを継承したインターフェースに継承させ、
 * 実装クラス側でEntityManagerを扱う処理を実装することで、JpaRepositoryを継承したインターフェースをProxy拡張させる
 */
@Repository
public interface UserRepositoryCustom {
  List<User> findAllUsers();
}

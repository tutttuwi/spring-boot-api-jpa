package com.example.system.domain.repository.systemdbref;

import com.example.system.domain.model.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserRepositoryRefCustom Proxy拡張 インターフェース<br/>
 * JpaRepositoryを継承したインターフェースに継承させ、
 * 実装クラス側でEntityManagerを扱う処理を実装することで、JpaRepositoryを継承したインターフェースをProxy拡張させる
 */
@Repository
public interface UserRepositoryRefCustom {
  List<User> findAllUsers();
}

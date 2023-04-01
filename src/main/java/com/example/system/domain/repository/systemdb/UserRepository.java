package com.example.system.domain.repository.systemdb;

import com.example.system.domain.model.entity.User;
import com.example.system.domain.model.entity.UserEmail;
import com.example.system.domain.model.entity.User_;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository
    extends JpaRepository<User, Long>, UserRepositoryCustom, JpaSpecificationExecutor {

}

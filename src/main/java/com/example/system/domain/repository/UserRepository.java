package com.example.system.domain.repository;

import com.example.system.domain.model.entity.User;
import com.example.system.domain.model.entity.UserEmail;
import com.example.system.domain.model.entity.User_;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom, JpaSpecificationExecutor {

    private static Specification<User> specFstNameEqual(User user) {
        return StringUtils.isEmpty(user.getFstName()) ? null : (r, cq, cb) ->
                cb.equal(r.get(User_.FST_NAME), user.getFstName());
    }

    private static Specification<User> specLstNameEqual(User user) {
        return StringUtils.isEmpty(user.getFstName()) ? null : (r, cq, cb) ->
                cb.equal(r.get(User_.LST_NAME), user.getLstName());
    }

    default List<User> findAllUserList(User user) {
        Specification<User> spec = Specification.where(specFstNameEqual(user)).and(specLstNameEqual(user));
//        return this.findAll(spec);
        return this.findAll((r, cq, cb) -> {
            Fetch<User, UserEmail> fetch = r.fetch(User_.userEmailList, JoinType.LEFT);
            return spec.toPredicate(r, cq, cb);
        });
//        return this.findAll((r, cq, cb) -> {
//            r.get(User_.fstName);
//            Specification.where(specFstNameEqual(user));
//            cq.select(r).where(cb.equal(r.get(User_.FST_NAME), user.getFstName()));
//
//            Fetch<User, User> fetch = r.fetch(User_.USER_ID);
//        });
    }

}

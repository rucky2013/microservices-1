package org.yvasilchuk.specification;

import org.springframework.data.jpa.domain.Specification;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.user.UserSearchModel;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private UserSearchModel criteria;

    public UserSpecification(UserSearchModel criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<String> usernamePath = root.get("username");
        Path<String> emailPath = root.get("email");

        final List<Predicate> predicates = new ArrayList<>();

        if (criteria.getUsername() != null) {
            Predicate predicate = cb.like(usernamePath, criteria.getUsername());
            predicates.add(predicate);
        }
        if (criteria.getEmail() != null) {
            Predicate predicate = cb.like(emailPath, criteria.getEmail());
            predicates.add(predicate);
        }

        return cb.or(predicates.toArray(new Predicate[predicates.size()]));
    }
}

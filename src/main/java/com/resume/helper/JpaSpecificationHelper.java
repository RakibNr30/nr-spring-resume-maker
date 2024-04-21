package com.resume.helper;

import com.resume.entity.cms.Interest;
import com.resume.entity.cms.Language;
import com.resume.entity.cms.Skill;
import com.resume.entity.cms.SocialAccount;
import com.resume.entity.ums.Role;
import com.resume.entity.ums.User;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashSet;
import java.util.Set;

public class JpaSpecificationHelper {

    public static Specification<User> hasRole(String roleName) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Join<User, Role> userRoleJoin = root.join("roles");
            return criteriaBuilder.equal(userRoleJoin.get("name"), roleName);
        };
    }

    public static <E> Specification<E> searchQuery(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Set<Predicate> likePredicates = new HashSet<>();

            for (SingularAttribute<?, ?> attribute : root.getModel().getDeclaredSingularAttributes()) {
                if (attribute.getJavaType().equals(String.class)) {
                    likePredicates.add(getLikePredicate(criteriaBuilder, root, attribute, keyword));
                }
                else if (isNamedEntityAttribute(attribute)) {
                    likePredicates.add(getNamedEntityPredicate(criteriaBuilder, root, attribute, keyword));
                }
            }

            return criteriaBuilder.or(likePredicates.toArray(new Predicate[0]));
        };
    }

    private static <E> Predicate getLikePredicate(CriteriaBuilder criteriaBuilder, Root<E> root, SingularAttribute<?, ?> attribute, String keyword) {
        Path<String> attributePath = root.get(attribute.getName());

        return criteriaBuilder.like(criteriaBuilder.lower(attributePath), "%" + keyword.trim().toLowerCase() + "%");
    }

    private static <E> Predicate getNamedEntityPredicate(CriteriaBuilder criteriaBuilder, Root<E> root, SingularAttribute<?, ?> attribute, String keyword) {
        Path<String> attributePath = root.get(attribute.getName()).get("name");

        return criteriaBuilder.like(criteriaBuilder.lower(attributePath), "%" + keyword.trim().toLowerCase() + "%");
    }

    private static boolean isNamedEntityAttribute(SingularAttribute<?, ?> attribute) {
        return Set
                .of(User.class, Interest.class, Language.class, Skill.class, SocialAccount.class)
                .contains(attribute.getJavaType());
    }
}

package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Specifications {

    public Specification<Landmark> getLandmarkSpecification(String name, Importance importance, Boolean active) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("active"), active));

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }

            if (importance != null) {
                predicates.add(criteriaBuilder.equal(root.get("importance"), importance));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

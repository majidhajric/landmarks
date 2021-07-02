package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Specifications {

    public Specification<Landmark> getLandmarkSpecification(String name, List<Importance> importanceList, Boolean active) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("active"), active));

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));
            }

            if (importanceList != null) {
                Path<Importance> importance = root.get("importance");
                predicates.add(importance.in(importanceList));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.nasa.stocktrack.infra.persistence.specifications;

import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<ProductEntity> byName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        likePattern(name)
                );
    }

    public static Specification<ProductEntity> byCode(String code) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("code")),
                        likePattern(code)
                );
    }

    private static String likePattern(String value) {
        value = value == null || value.isBlank() ? "" : value;

        return "%" + value.toLowerCase() + "%";
    }
}

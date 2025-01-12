package com.example.testplugin.controller;

import com.example.testplugin.model.TestUser;
import org.springframework.data.jpa.domain.Specification;

/**
 * This is a generated Specs for demonstration purposes.
 * Both keywords, namely Field and FieldKey in the method, should be manually modified.
 */
public class TestUserSpecs {
    public static Specification<TestUser> equalByUsername(String fieldValue) {
        return (root, query, builder) -> {
            return builder.equal(root.get("username"), fieldValue);
        };
    }

    public static Specification<TestUser> notEqualByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.notEqual(root.get("fieldKey"), fieldValue);
        };
    }

    public static Specification<TestUser> greaterThanByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.greaterThan(root.get("fieldKey"), fieldValue);
        };
    }

    public static Specification<TestUser> greaterThanOrEqualToByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.greaterThanOrEqualTo(root.get("fieldKey"), fieldValue);
        };
    }

    public static Specification<TestUser> lessThanByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get("fieldKey"), fieldValue);
        };
    }

    public static Specification<TestUser> lessThanOrEqualToByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.lessThanOrEqualTo(root.get("fieldKey"), fieldValue);
        };
    }

    public static Specification<TestUser> likeByField(String fieldValue) {
        return (root, query, builder) -> {
            return builder.like(root.get("fieldKey"), fieldValue);
        };
    }
}
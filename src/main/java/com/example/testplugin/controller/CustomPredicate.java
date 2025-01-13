package com.example.testplugin.controller;

import java.util.Collection;
import java.util.List;

import com.example.testplugin.model.TestUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;

public class CustomPredicate {

    private String field;         // 查询字段
    private Object value;         // 查询值
    private Operator operator;    // 查询操作符

    public CustomPredicate(String field, Object value, Operator operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    // 操作符枚举
    public enum Operator {
        EQUAL, LIKE, GREATER_THAN, LESS_THAN, IN
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }
  
    public Predicate toJpaPredicate(CriteriaBuilder cb, Root<?> root) {
        switch (getOperator()) {
            case EQUAL:
                return cb.equal(root.get(getField()), getValue());
            case LIKE:
                return cb.like(root.get(getField()), "%" + getValue() + "%");
            case GREATER_THAN:
                return cb.greaterThan(root.get(getField()), (Comparable) getValue());
            case LESS_THAN:
                return cb.lessThan(root.get(getField()), (Comparable) getValue());
            case IN:
                CriteriaBuilder.In<Object> in = cb.in(root.get(getField()));
                if (getValue() instanceof Collection) {
                    for (Object value : (Collection<?>) getValue()) {
                        in.value(value);
                    }
                }
                return in;
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + getOperator());
        }
    }

}




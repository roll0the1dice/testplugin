package com.example.testplugin.controller;

import com.example.testplugin.model.TestUser;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

/**
 * This is a generated BaseService for demonstration purposes.
 */
@Data
public class CustomSpecs<T>  implements Specification<T> {
    private String field;

    /** This is an example. */
    private Object value;

    /** This is an example. */
    private Operator operator;

    /** This is an example. */
    private LinkedList<Specification<T>> specs;

    /** This is an example. */
    private LinkedList<Operator> boolOps;

    public CustomSpecs() {
        this.specs = new LinkedList<Specification<T>>();
        this.boolOps = new LinkedList<Operator>();
    }

    @Nullable
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

    public Specification<T> _and() {
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public CustomSpecs<T> _or() {
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.OR);
        return this;
    }

    public CustomSpecs<T> _equal(String fieldKey, String fieldValue) {
        setField(fieldKey);
        setValue(fieldValue);
        setOperator(Operator.EQUAL);
        specs.add((root, query, builder) -> {
            return  toPredicate(root, query, builder);
        });
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public CustomSpecs<T> _like(String fieldKey, String fieldValue) {
        setField(fieldKey);
        setValue(fieldValue);
        setOperator(Operator.LIKE);
        specs.add((root, query, builder) -> {
            return  toPredicate(root, query, builder);
        });
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public CustomSpecs<T> _greaterThan(String fieldKey, String fieldValue) {
        setField(fieldKey);
        setValue(fieldValue);
        setOperator(Operator.GREATER_THAN);
        specs.add((root, query, builder) -> {
            return  toPredicate(root, query, builder);
        });
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public CustomSpecs<T> _lessThan(String fieldKey, String fieldValue) {
        setField(fieldKey);
        setValue(fieldValue);
        setOperator(Operator.LESS_THAN);
        specs.add((root, query, builder) -> {
            return  toPredicate(root, query, builder);
        });
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public CustomSpecs<T> _in(String fieldKey, String fieldValue) {
        setField(fieldKey);
        setValue(fieldValue);
        setOperator(Operator.IN);
        specs.add((root, query, builder) -> {
            return  toPredicate(root, query, builder);
        });
        if (boolOps.size() < specs.size())
        boolOps.add(Operator.AND);
        return this;
    }

    public Specification<T> _generateSpecifications() {
        Specification<T> combinedSpec = Specification.where(null);
        assert(specs.size() == boolOps.size());
        for (int i = 0; i < specs.size() && i < boolOps.size(); i++) {
            var spec = specs.remove(0);
            var op = boolOps.remove(0);
            switch (op) {
            case OR:
                combinedSpec = combinedSpec.or(spec);
                break;
            case AND:
                default:
                    combinedSpec = combinedSpec.and(spec);
                    break;
                }
            }
            return combinedSpec;
    }

    enum Operator {

         EQUAL, LIKE, GREATER_THAN, LESS_THAN, IN, AND, OR;
    }
}
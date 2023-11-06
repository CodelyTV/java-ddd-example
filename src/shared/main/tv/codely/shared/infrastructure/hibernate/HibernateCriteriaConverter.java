package tv.codely.shared.infrastructure.hibernate;

import jakarta.persistence.criteria.*;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filter;
import tv.codely.shared.domain.criteria.FilterOperator;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class HibernateCriteriaConverter<T> {
    private final CriteriaBuilder builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>>() {{
        put(FilterOperator.EQUAL, HibernateCriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, HibernateCriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, HibernateCriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.LT, HibernateCriteriaConverter.this::lowerThanPredicateTransformer);
        put(FilterOperator.CONTAINS, HibernateCriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, HibernateCriteriaConverter.this::notContainsPredicateTransformer);
    }};

    public HibernateCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T>          root              = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.filters().filters(), root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().value());
            Order        order   = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);

            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(
            filter,
            root
        )).collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());

        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.equal(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notEqual(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.greaterThan(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        return builder.lessThan(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }
}

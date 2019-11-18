package tv.codely.shared.domain.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class Filters {
    private final List<Filter> filters;

    public Filters(List<Filter> filters) {
        this.filters = filters;
    }

    public static Filters fromValues(List<HashMap<String, String>> filters) {
        return new Filters(filters.stream().map(Filter::fromValues).collect(Collectors.toList()));
    }

    public List<Filter> filters() {
        return filters;
    }
}

package tv.codely.mooc.courses.application.search_last;

import tv.codely.shared.domain.bus.query.Query;

public final class SearchLastCoursesQuery implements Query {
    private final Integer total;

    public SearchLastCoursesQuery(Integer total) {
        this.total = total;
    }

    public Integer total() {
        return total;
    }
}

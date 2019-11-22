package tv.codely.mooc.courses.application.search_last;

import tv.codely.shared.domain.bus.query.Query;

import java.util.Objects;

public final class SearchLastCoursesQuery implements Query {
    private final Integer total;

    public SearchLastCoursesQuery(Integer total) {
        this.total = total;
    }

    public Integer total() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchLastCoursesQuery that = (SearchLastCoursesQuery) o;
        return total.equals(that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }
}

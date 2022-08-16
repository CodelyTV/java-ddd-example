package tv.codely.mooc.courses.application.search_last;

import tv.codely.shared.domain.IntegerMother;

public final class SearchLastCoursesQueryMother {
    public static SearchLastCoursesQuery create(Integer total) {
        return new SearchLastCoursesQuery(total);
    }

    public static SearchLastCoursesQuery random() {
        return create(IntegerMother.random());
    }
}

package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class FindAllCoursesQueryHandler implements QueryHandler<FindAllCoursesQuery, CoursesResponse> {
    private final AllCoursesFinder finder;

    public FindAllCoursesQueryHandler(AllCoursesFinder finder) {
        this.finder = finder;
    }

    @Override
    public CoursesResponse handle(FindAllCoursesQuery query) {
        return finder.findAll();
    }
}

package tv.codely.mooc.courses.application.find;

import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.mooc.courses.application.CoursesResponse;

@Service
public final class FindAllCoursesQueryHandler implements QueryHandler<FindCoursesQuery, CoursesResponse> {

	private final CoursesFinder finder;

	public FindAllCoursesQueryHandler(CoursesFinder finder) {
		this.finder = finder;
	}

	@Override
	public CoursesResponse handle(FindCoursesQuery query) {
		return finder.findAll();
	}
}

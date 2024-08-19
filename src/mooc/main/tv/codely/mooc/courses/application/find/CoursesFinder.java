package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.stream.Collectors;

@Service
public final class CoursesFinder {

	private final CourseRepository repository;


	public CoursesFinder(CourseRepository repository) {
		this.repository = repository;
	}

	public CoursesResponse findAll() throws CourseNotExist {
		return new CoursesResponse(
			repository.findAll()
				.stream()
				.map(CourseResponse::fromAggregate)
				.collect(Collectors.toList()));
	}


}

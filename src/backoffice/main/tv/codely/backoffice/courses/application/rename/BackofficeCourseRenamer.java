package tv.codely.backoffice.courses.application.rename;

import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class BackofficeCourseRenamer {
	private final BackofficeCourseRepository repository;

	public BackofficeCourseRenamer(BackofficeCourseRepository repository) {
		this.repository = repository;
	}

	public void rename(String id, String name) {
		this.repository.search(id)
			.ifPresent(course -> {
				course.rename(name);

				this.repository.save(course);
			});
	}
}

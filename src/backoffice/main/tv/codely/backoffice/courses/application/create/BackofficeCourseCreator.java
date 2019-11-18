package tv.codely.backoffice.courses.application.create;

import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class BackofficeCourseCreator {
    private final BackofficeCourseRepository repository;

    public BackofficeCourseCreator(BackofficeCourseRepository repository) {
        this.repository = repository;
    }

    public void create(String id, String name, String duration) {
        this.repository.save(new BackofficeCourse(id, name, duration));
    }
}

package tv.codely.mooc.courses.domain;

import java.util.Optional;

public final class CourseFinder {
    private final CourseRepository repository;

    public CourseFinder(CourseRepository repository) {
        this.repository = repository;
    }

    public Optional<Course> find(CourseId id) {
        return repository.search(id);
    }
}

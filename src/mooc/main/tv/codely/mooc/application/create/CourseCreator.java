package tv.codely.mooc.application.create;

import tv.codely.mooc.domain.CourseRepository;
import tv.codely.mooc.domain.Course;
import tv.codely.shared.domain.Service;

@Service
public final class CourseCreator {

    private final CourseRepository repository;

    public CourseCreator(CourseRepository repository) {
        this.repository = repository;
    }

    public void create(String id, String name, String duration) {
        Course course = new Course(id, name, duration);

        repository.save(course);
    }
}

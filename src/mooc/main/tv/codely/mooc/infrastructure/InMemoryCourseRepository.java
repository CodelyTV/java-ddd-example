package tv.codely.mooc.infrastructure;

import tv.codely.mooc.domain.CourseRepository;
import tv.codely.mooc.domain.Course;
import tv.codely.shared.domain.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class InMemoryCourseRepository implements CourseRepository {
    private HashMap<String, Course> courses = new HashMap<>();

    @Override
    public void save(Course course) {
        this.courses.put(course.getId(), course);
    }

    @Override
    public Optional<Course> search(String id) {
        return Optional.ofNullable(courses.get(id));
    }
}

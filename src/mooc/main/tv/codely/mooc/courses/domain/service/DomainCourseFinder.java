package tv.codely.mooc.courses.domain.service;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;

public class DomainCourseFinder {
    private final CourseRepository courseRepository;


    public DomainCourseFinder(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course find(CourseId id) throws CourseNotExist {
        return courseRepository.search(id)
            .orElseThrow(() -> new CourseNotExist(id));
    }
}

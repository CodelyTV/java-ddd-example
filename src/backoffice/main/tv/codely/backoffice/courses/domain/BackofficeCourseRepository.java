package tv.codely.backoffice.courses.domain;

import java.util.List;

public interface BackofficeCourseRepository {
    void save(BackofficeCourse course);

    List<BackofficeCourse> searchAll();
}

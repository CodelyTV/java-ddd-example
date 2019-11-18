package tv.codely.backoffice.courses.domain;

import tv.codely.shared.domain.criteria.Criteria;

import java.util.List;

public interface BackofficeCourseRepository {
    void save(BackofficeCourse course);

    List<BackofficeCourse> searchAll();

    List<BackofficeCourse> matching(Criteria criteria);
}

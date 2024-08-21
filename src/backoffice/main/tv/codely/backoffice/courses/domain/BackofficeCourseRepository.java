package tv.codely.backoffice.courses.domain;

import tv.codely.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface BackofficeCourseRepository {
    void save(BackofficeCourse course);

    Optional<BackofficeCourse> search(String id);

	List<BackofficeCourse> searchAll();

    List<BackofficeCourse> matching(Criteria criteria);
}

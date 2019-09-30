package tv.codely.mooc.courses.infrastructure.persistence;

import org.hibernate.SessionFactory;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MySqlCourseRepository implements CourseRepository {
    private SessionFactory sessionFactory;

    public MySqlCourseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public Optional<Course> search(CourseId id) {
        return Optional.empty();
    }
}

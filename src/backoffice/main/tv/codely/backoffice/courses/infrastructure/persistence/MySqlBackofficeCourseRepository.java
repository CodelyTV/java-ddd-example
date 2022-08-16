package tv.codely.backoffice.courses.infrastructure.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

import java.util.List;

@Service
@Transactional("backoffice-transaction_manager")
public class MySqlBackofficeCourseRepository extends HibernateRepository<BackofficeCourse> implements BackofficeCourseRepository {
    public MySqlBackofficeCourseRepository(@Qualifier("backoffice-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, BackofficeCourse.class);
    }

    @Override
    public void save(BackofficeCourse course) {
        persist(course);
    }

    @Override
    public List<BackofficeCourse> searchAll() {
        return all();
    }

    @Override
    public List<BackofficeCourse> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}

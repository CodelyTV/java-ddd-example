package tv.codely.mooc.courses_counter.infrastructure.persistence;

import org.hibernate.SessionFactory;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Service
public final class MySqlCoursesCounterRepository extends HibernateRepository<CoursesCounter> implements CoursesCounterRepository {
    public MySqlCoursesCounterRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CoursesCounter.class);
    }

    @Override
    public void save(CoursesCounter counter) {
        persist(counter);
    }

    @Override
    public Optional<CoursesCounter> search() {
        CriteriaBuilder               builder  = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<CoursesCounter> criteria = builder.createQuery(aggregateClass);
        criteria.from(aggregateClass);
        List<CoursesCounter> coursesCounter = sessionFactory.getCurrentSession().createQuery(criteria).getResultList();

        return 0 == coursesCounter.size() ? Optional.empty() : Optional.ofNullable(coursesCounter.get(0));
    }
}

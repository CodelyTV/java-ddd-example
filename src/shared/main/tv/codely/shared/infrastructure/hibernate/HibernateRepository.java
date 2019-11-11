package tv.codely.shared.infrastructure.hibernate;

import org.hibernate.SessionFactory;
import tv.codely.shared.domain.Identifier;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public abstract class HibernateRepository<T> {
    protected final SessionFactory sessionFactory;
    protected final Class<T>       aggregateClass;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> aggregateClass) {
        this.sessionFactory = sessionFactory;
        this.aggregateClass = aggregateClass;
    }

    protected void persist(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    protected Optional<T> byId(Identifier id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().byId(aggregateClass).load(id));
    }
}

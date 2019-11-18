package tv.codely.shared.infrastructure.hibernate;

import org.hibernate.SessionFactory;
import tv.codely.shared.domain.Identifier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

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

    protected List<T> all() {
        CriteriaBuilder  builder  = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(aggregateClass);
        criteria.from(aggregateClass);

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}

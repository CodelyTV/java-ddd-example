package tv.codely.shared.infrastructure.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import tv.codely.shared.domain.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public class DatabaseCleaner {
    private SessionFactory sessionFactory;

    public DatabaseCleaner(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void clean() {
        List<String>               tableNames        = new ArrayList<>();
        Session                    session           = sessionFactory.getCurrentSession();
        Map<String, ClassMetadata> hibernateMetadata = session.getSessionFactory().getAllClassMetadata();
        EntityManager              entityManager     = session.getEntityManagerFactory().createEntityManager();

        for (ClassMetadata classMetadata : hibernateMetadata.values()) {
            AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
            tableNames.add(aep.getTableName());
        }

        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}

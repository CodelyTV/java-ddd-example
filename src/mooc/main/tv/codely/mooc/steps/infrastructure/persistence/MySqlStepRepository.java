package tv.codely.mooc.steps.infrastructure.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.infrastructure.hibernate.HibernateRepository;

import java.util.Optional;

@Service
@Transactional("mooc-transaction_manager")
public class MySqlStepRepository extends HibernateRepository<Step> implements StepRepository {
    public MySqlStepRepository(@Qualifier("mooc-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Step.class);
    }

    @Override
    public void save(Step step) {
        persist(step);
    }

    @Override
    public Optional<? extends Step> search(StepId id) {
        return byId(id);
    }
}

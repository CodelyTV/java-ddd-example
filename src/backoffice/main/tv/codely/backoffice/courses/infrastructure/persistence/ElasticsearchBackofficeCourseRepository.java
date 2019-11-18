package tv.codely.backoffice.courses.infrastructure.persistence;

import org.springframework.context.annotation.Primary;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchClient;
import tv.codely.shared.infrastructure.elasticsearch.ElasticsearchRepository;

import java.util.List;

@Primary
@Service
public final class ElasticsearchBackofficeCourseRepository extends ElasticsearchRepository<BackofficeCourse> implements BackofficeCourseRepository {
    public ElasticsearchBackofficeCourseRepository(ElasticsearchClient client) {
        super(client);
    }

    @Override
    public void save(BackofficeCourse course) {
        persist(course.id(), course.toPrimitives());
    }

    @Override
    public List<BackofficeCourse> searchAll() {
        return searchAllInElastic(BackofficeCourse::fromPrimitives);
    }

    @Override
    public List<BackofficeCourse> matching(Criteria criteria) {
        return searchByCriteria(criteria, BackofficeCourse::fromPrimitives);
    }

    @Override
    protected String moduleName() {
        return "courses";
    }
}

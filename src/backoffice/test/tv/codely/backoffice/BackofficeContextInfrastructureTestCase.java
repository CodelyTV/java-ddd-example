package tv.codely.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.backoffice.courses.ElasticsearchEnvironmentArranger;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

import java.io.IOException;

@ContextConfiguration(classes = BackofficeFrontendApplication.class)
@SpringBootTest
public abstract class BackofficeContextInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    private ElasticsearchEnvironmentArranger elasticsearchArranger;

    protected void clearElasticsearch() throws IOException {
        elasticsearchArranger.arrange("backoffice", "backoffice_courses");
    }
}

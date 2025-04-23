package tv.codely.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.backoffice.courses.ElasticsearchEnvironmentArranger;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

import java.io.IOException;
import java.util.logging.Logger;

@ContextConfiguration(classes = BackofficeFrontendApplication.class)
@SpringBootTest
public abstract class BackofficeContextInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    private ElasticsearchEnvironmentArranger elasticsearchArranger;

    private static final Logger logger = Logger.getLogger(BackofficeContextInfrastructureTestCase.class.getName());
    protected void clearElasticsearch() throws IOException {
        logger.info("Clearing Elasticsearch indices: backoffice, backoffice_courses");
        elasticsearchArranger.arrange("backoffice", "backoffice_courses");
    }
}
//Add the logging function

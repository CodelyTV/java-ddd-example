package tv.codely.backoffice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.backoffice.frontend.BackofficeFrontendApplication;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

@ContextConfiguration(classes = BackofficeFrontendApplication.class)
@SpringBootTest
public abstract class BackofficeContextInfrastructureTestCase extends InfrastructureTestCase {
}

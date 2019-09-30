package tv.codely.shared.infrastructure;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tv.codely.apps.Starter;

@ContextConfiguration(classes = Starter.class)
@SpringBootTest
public abstract class InfrastructureTestCase {
}

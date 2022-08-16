package tv.codely.apps.mooc;

import org.springframework.transaction.annotation.Transactional;
import tv.codely.apps.ApplicationTestCase;

@Transactional("mooc-transaction_manager")
public abstract class MoocApplicationTestCase extends ApplicationTestCase {
}

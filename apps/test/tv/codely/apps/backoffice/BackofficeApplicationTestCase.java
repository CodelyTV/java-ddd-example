package tv.codely.apps.backoffice;

import org.springframework.transaction.annotation.Transactional;

import tv.codely.apps.ApplicationTestCase;

@Transactional("backoffice-transaction_manager")
public abstract class BackofficeApplicationTestCase extends ApplicationTestCase {}

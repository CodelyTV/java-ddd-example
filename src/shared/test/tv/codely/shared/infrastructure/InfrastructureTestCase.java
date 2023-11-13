package tv.codely.shared.infrastructure;

public abstract class InfrastructureTestCase {
    private final int MAX_ATTEMPTS                   = 3;
    private final int MILLIS_TO_WAIT_BETWEEN_RETRIES = 700;

	protected void eventually(Runnable assertion) throws Exception {
		int attempts = 0;

		while (true) {
			try {
				assertion.run();
				return;
			} catch (Throwable error) {
				attempts++;

				if (attempts >= MAX_ATTEMPTS) {
					throw new Exception(
						String.format("Could not assert after %d retries. Last error: %s", MAX_ATTEMPTS, error.getMessage()),
						error
					);
				}

				Thread.sleep(MILLIS_TO_WAIT_BETWEEN_RETRIES);
			}
		}
	}
}

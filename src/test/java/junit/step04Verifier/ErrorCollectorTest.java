package junit.step04Verifier;

import static org.hamcrest.Matchers.*;
import org.junit.*;
import org.junit.rules.ErrorCollector;

/**
 * Take a look at Verifier code.
 */
public class ErrorCollectorTest {
	@Rule
	public ErrorCollector assertions = new ErrorCollector();

	@Test
	public void canCollectAllErrors() {
		for (int i = 0; i < 10; i++) {
			assertions.checkThat(i, lessThan(10));
		}
	}
}

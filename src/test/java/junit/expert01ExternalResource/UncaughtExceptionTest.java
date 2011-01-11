package junit.expert01ExternalResource;

import org.junit.*;
import org.junit.rules.Timeout;
import rules.UncaughtException;

public class UncaughtExceptionTest {
	@Rule
	public Timeout timeout = new Timeout(5000);

	@Rule
	public UncaughtException uncaughtException = new UncaughtException();

	@Test
	public void shouldFailInSeparateThread() throws InterruptedException {
		Thread thread = new Thread() {
			@Override
			public void run() {
				throw new RuntimeException("BUG");
			}
		};
		thread.start();
		thread.join();

		//Assertions.assertThat(uncaughtException.firstUncaughtException()).hasMessage("BUG");
	}
}

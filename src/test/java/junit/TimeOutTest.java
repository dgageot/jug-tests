package junit;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.rules.Timeout;

public class TimeOutTest {
	@Test(timeout = 100)
	public void canFailIfItTakesTooLong() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}

	@Rule
	public Timeout timeout = new Timeout(100);

	@Test
	public void versionWithRule() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}

	@Test
	public void anotherTest() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(10);
	}
}

package junit.step04TestWatchman;

import org.fest.assertions.Fail;
import org.junit.*;
import org.junit.rules.TestName;

/**
 * The interesting part is in TestWatchman implementation.
 */
public class TestNameTest {
	@Rule
	public TestName testName = new TestName();

	@Test
	public void dontFail() {
		if ("fail".equals(testName.getMethodName())) {
			Fail.fail("This test should always fail");
		}
	}
}

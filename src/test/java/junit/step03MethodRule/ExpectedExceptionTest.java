package junit.step03MethodRule;

import static org.fest.assertions.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.fest.assertions.Fail;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class ExpectedExceptionTest {
	@Test
	public void shouldFailForAReason() {
		try {
			TestedCode.doSomething("reason");
			Fail.fail("previous call should fail!");
		} catch (Throwable e) {
			assertTrue(e instanceof IllegalStateException);
			assertTrue(e.getMessage().contains("Failed because of reason"));
		}
	}

	static class TestedCode {
		public static void doSomething(String reason) {
			throw new IllegalStateException("Failed because of " + reason);
		}
	}

	@Test
	public void withHamcrestMatchers() {
		try {
			TestedCode.doSomething("reason");
			Fail.fail("previous call should fail!");
		} catch (Throwable e) {
			Assert.assertThat(e, instanceOf(IllegalStateException.class));
			Assert.assertThat(e.getMessage(), containsString("Failed because of reason"));
		}
	}

	@Test
	public void withFestAssert() {
		try {
			TestedCode.doSomething("reason");
			Fail.fail("previous call should fail!");
		} catch (Throwable e) {
			assertThat(e).isInstanceOf(IllegalStateException.class).hasMessage("Failed because of reason");
		}
	}

	@Test(expected = IllegalStateException.class)
	public void withJUnit4() {
		TestedCode.doSomething("reason");
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void withRule() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Failed because of reason");

		TestedCode.doSomething("reason");
	}
}

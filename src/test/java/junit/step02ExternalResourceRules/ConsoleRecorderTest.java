package junit.step02ExternalResourceRules;

import static org.fest.assertions.Assertions.*;
import org.junit.*;
import rules.ConsoleRecorder;

/**
 * Homemade rule.
 */
public class ConsoleRecorderTest {
	@Rule
	public ConsoleRecorder console = ConsoleRecorder.recordSystemErr();

	@Test
	public void canOutputUsageToConsole() {
		Main.main(new String[0]);

		assertThat(console.getOutput()).startsWith("Usage");
	}

	static class Main {
		public static void main(String[] args) {
			System.err.println("Usage...");
		}
	}
}

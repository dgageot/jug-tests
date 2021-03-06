package junit.step03MethodRule;

import static org.fest.assertions.Assertions.*;
import java.util.List;
import org.junit.*;
import rules.RunTestsMultipleTimes;
import com.google.common.collect.Lists;

// Change to 10 and add System.out.println("Run test");
// Try with 10000, 100. See if it fails.
//
public class MultiThreadTest {
	@Rule
	public RunTestsMultipleTimes runTestsMultipleTimes = new RunTestsMultipleTimes(1);

	@Test
	public void canMakeSureItsThreadSafe() {
		CodeUnderTest code = new CodeUnderTest();

		int previousSize = code.values().size();
		code.add("A");
		code.add("B");
		code.add("C");

		assertThat(code.values()).hasSize(previousSize + 3);
	}

	static class CodeUnderTest {
		private final static List<String> values = Lists.newArrayList();

		public void add(String value) {
			values.add(value);
		}

		public List<String> values() {
			return values;
		}
	}
}

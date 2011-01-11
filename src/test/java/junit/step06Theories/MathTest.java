package junit.step06Theories;

import static org.fest.assertions.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.*;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MathTest {
	@DataPoints
	public static int[] positiveValues = { 0, 1, 10, 20, 30, 100 };

	@Theory
	public void additionShouldBeCommutative(int value1, int value2) {
		System.out.println(value1 + "/" + value2);

		int result1 = TestedCode.add(value1, value2);
		int result2 = TestedCode.add(value1, value2);

		assertThat(result1).isEqualTo(result2);
	}

	@Theory
	public void integerDivisionShouldRoundDown(int value1, int value2) {
		assumeThat(value2, not(0));

		int result = TestedCode.divide(value1, value2);

		assertThat(result * value2).isLessThanOrEqualTo(value1);
	}

	private static class TestedCode {
		static int add(int value1, int value2) {
			return value1 + value2;
		}

		static int divide(int value1, int value2) {
			return value1 / value2;
		}
	}

	// Add more datapoints or Datapoint
}

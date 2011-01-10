package festassert;

import static org.fest.assertions.Assertions.*;
import org.junit.Test;

public class EmployeesTest {
	@Test
	public void canHaveZeroEmployee() {
		Employees employees = new Employees();

		assertThat(employees).isEmpty();
	}

	@Test
	public void canCountEmployees() {
		Employees employees = new Employees();

		employees.add("Bernard");
		employees.add("Jack");

		assertThat(employees) //
				.hasSize(2) //
				.contains("Bernard").contains("Jack") //
				.excludes("John") //
				.containsOnly("Bernard", "Jack");
	}

	@Test
	public void canCompareEmployee() {
		Employees employees = new Employees();

		employees.add("Bernard");

		assertThat(employees.get(0)) //
				.isNotEmpty() //
				.isEqualTo("Bernard") //
				.isEqualToIgnoringCase("BERNARD") //
				.startsWith("Ber") //
				.endsWith("nard");
	}
}
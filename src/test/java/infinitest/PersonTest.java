package infinitest;

import static org.fest.assertions.Assertions.*;
import org.junit.*;

/**
 * Take a look at configuration files infinitest.args and infinitest.filters.
 */
public class PersonTest {
	@Test
	public void canCreatePerson() {
		Person person = new Person("David", 35);

		assertThat(person.getName()).isSameAs("David");
		assertThat(person.getAge()).isEqualTo(35);
	}

	@Test
	@Ignore
	public void canMakeInfinitestFail() {
		Person person = new Person("David", 35);

		assertThat(person.getName()).isSameAs("David");
		assertThat(person.getAge()).isGreaterThanOrEqualTo(65);
	}
}

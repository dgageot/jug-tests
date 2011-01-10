package moreunit;

/**
 * Press cmd-T to create a test (including its package).<br/>
 * Press cmd-T to jump from test to code, form code to test.<br/>
 * Rename class and see the test being renamed.
 */
public class Pojo {
	private final String name;

	private Pojo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

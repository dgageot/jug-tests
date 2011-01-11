package junit.step02ExternalResourceRules;

import static org.fest.assertions.Assertions.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

public class ListFilesWithStandardRuleTest {
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void canCountFiles() throws IOException {
		File folder = temporaryFolder.getRoot();

		new File(folder, "A.txt").createNewFile();
		new File(folder, "B.txt").createNewFile();

		assertThat(folder.listFiles()).hasSize(2);
	}
}

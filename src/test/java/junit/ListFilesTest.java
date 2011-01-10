package junit;

import static org.fest.assertions.Assertions.*;
import java.io.*;
import org.junit.*;
import com.google.common.io.Files;

public class ListFilesTest {
	private File folder;

	@Before
	public void createFolder() throws IOException {
		folder = File.createTempFile("folder", "");
		folder.delete();
		folder.mkdir();
	}

	@After
	public void cleanUp() throws IOException {
		Files.deleteDirectoryContents(folder);
	}

	@Test
	public void canCountFiles() throws IOException {
		new File(folder, "A.txt").createNewFile();
		new File(folder, "B.txt").createNewFile();

		assertThat(folder.listFiles()).hasSize(2);
	}
}

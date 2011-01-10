package junit;

import static org.fest.assertions.Assertions.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.ExternalResource;
import com.google.common.io.Files;

public class ExternalResourceTest {
	File folder;

	@Rule
	public ExternalResource createFolder = new ExternalResource() {
		@Override
		protected void before() throws IOException {
			folder = File.createTempFile("folder", "");
			folder.delete();
			folder.mkdir();
		}

		@Override
		protected void after() {
			try {
				Files.deleteDirectoryContents(folder);
			} catch (IOException e) {
				// Ignore
			}
		}
	};

	@Test
	public void canCountFiles() throws IOException {
		new File(folder, "A.txt").createNewFile();
		new File(folder, "B.txt").createNewFile();

		assertThat(folder.listFiles()).hasSize(2);
	}
}

package junit;

import static org.fest.assertions.Assertions.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.ExternalResource;
import com.google.common.io.Files;

public class ExternalResourceTest {
	@Rule
	public TempFolder tempFolder = new TempFolder();

	@Test
	public void canCountFiles() throws IOException {
		File folder = tempFolder.getFolder();

		new File(folder, "A.txt").createNewFile();
		new File(folder, "B.txt").createNewFile();

		assertThat(folder.listFiles()).hasSize(2);
	}

	static class TempFolder extends ExternalResource {
		private File folder;

		public File getFolder() {
			return folder;
		}

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
	}
}

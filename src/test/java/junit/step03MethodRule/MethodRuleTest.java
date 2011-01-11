package junit.step03MethodRule;

import static org.fest.assertions.Assertions.*;
import java.io.*;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.runners.model.*;
import com.google.common.io.Files;

/**
 * Same way of using a MethodRule or an ExternalResource Rule. Only the
 * implementation changes.
 */
public class MethodRuleTest {
	@Rule
	public TempFolder tempFolder = new TempFolder();

	@Test
	public void canCountFiles() throws IOException {
		File folder = tempFolder.getFolder();

		new File(folder, "A.txt").createNewFile();
		new File(folder, "B.txt").createNewFile();

		assertThat(folder.listFiles()).hasSize(2);
	}

	static class TempFolder implements MethodRule {
		private File folder;

		@Override
		public Statement apply(final Statement base, FrameworkMethod method, Object target) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					createFolder();
					try {
						base.evaluate();
					} finally {
						deleteFolder();
					}
				}
			};
		}

		public File getFolder() {
			return folder;
		}

		void createFolder() throws IOException {
			folder = File.createTempFile("folder", "");
			folder.delete();
			folder.mkdir();
		}

		void deleteFolder() {
			try {
				Files.deleteDirectoryContents(folder);
			} catch (IOException e) {
				// Ignore
			}
		}

	}
}

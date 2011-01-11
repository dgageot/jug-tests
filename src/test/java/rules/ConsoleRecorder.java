package rules;

import static rules.ConsoleRecorder.ConsoleType.*;
import java.io.*;
import org.junit.rules.ExternalResource;

/**
 * JUnit Rule to record System.out or System.err output.
 */
public class ConsoleRecorder extends ExternalResource {
	private final ConsoleType console;
	private final ByteArrayOutputStream recordedContent = new ByteArrayOutputStream();
	private final PrintStream recordingOutput = new PrintStream(recordedContent);

	private ConsoleRecorder(ConsoleType recorderType) {
		console = recorderType;
	}

	public static ConsoleRecorder recordSystemOut() {
		return new ConsoleRecorder(OUT);
	}

	public static ConsoleRecorder recordSystemErr() {
		return new ConsoleRecorder(ERR);
	}

	@Override
	protected void before() throws Throwable {
		console.redirectTo(recordingOutput);
	}

	@Override
	protected void after() {
		console.restore();
	}

	public String getOutput() {
		recordingOutput.flush();
		return recordedContent.toString();
	}

	static enum ConsoleType {
		OUT {
			@Override
			void redirectTo(PrintStream stream) {
				System.setOut(stream);
			}

			@Override
			void restore() {
				System.setOut(System.out);
			}
		},
		ERR {
			@Override
			void redirectTo(PrintStream stream) {
				System.setErr(stream);
			}

			@Override
			void restore() {
				System.setErr(System.err);
			}
		};

		abstract void redirectTo(PrintStream stream);

		abstract void restore();
	}
}
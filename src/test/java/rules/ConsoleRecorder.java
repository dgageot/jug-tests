package rules;

import static rules.ConsoleRecorder.ConsoleType.*;
import java.io.*;
import org.junit.rules.MethodRule;
import org.junit.runners.model.*;

/**
 * JUnit Rule to record System.out or System.err output.
 */
public class ConsoleRecorder implements MethodRule {
	static final Object MAKE_THIS_RULE_THREAD_SAFE = new Object();

	final ByteArrayOutputStream recordedContent = new ByteArrayOutputStream();
	final PrintStream recordingOutput = new PrintStream(recordedContent);
	final ConsoleType console;

	private ConsoleRecorder(ConsoleType recorderType) {
		console = recorderType;
	}

	public static ConsoleRecorder recordSystemOut() {
		return new ConsoleRecorder(OUT);
	}

	public static ConsoleRecorder recordSystemErr() {
		return new ConsoleRecorder(ERR);
	}

	public String getOutput() {
		recordingOutput.flush();
		return recordedContent.toString();
	}

	@Override
	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				synchronized (MAKE_THIS_RULE_THREAD_SAFE) {
					console.redirectTo(recordingOutput);
					try {
						base.evaluate();
					} finally {
						console.restore();
					}
				}
			}
		};
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
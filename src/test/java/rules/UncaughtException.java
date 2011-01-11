package rules;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import org.junit.rules.ExternalResource;
import com.google.common.collect.Lists;

public class UncaughtException extends ExternalResource {
	private UncaughtExceptionHandler currentUncaughtExceptionHandler;
	final List<Throwable> uncaughtExceptions = Lists.newArrayList();

	@Override
	protected void before() throws Throwable {
		currentUncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();

		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				uncaughtExceptions.add(e);
			}
		});
	}

	@Override
	protected void after() {
		Thread.setDefaultUncaughtExceptionHandler(currentUncaughtExceptionHandler);
	}

	public boolean hasUncaughtException() {
		return !uncaughtExceptions.isEmpty();
	}

	public Throwable firstUncaughtException() {
		return uncaughtExceptions.get(0);
	}
}
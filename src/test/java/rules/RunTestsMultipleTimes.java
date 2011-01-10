package rules;

import java.util.List;
import java.util.concurrent.*;
import org.junit.rules.MethodRule;
import org.junit.runners.model.*;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

public class RunTestsMultipleTimes implements MethodRule {
	final int threads;
	final int times;

	public RunTestsMultipleTimes(int times) {
		this(times, 1);
	}

	public RunTestsMultipleTimes(int times, int threads) {
		this.times = times;
		this.threads = threads;
	}

	@Override
	public Statement apply(final Statement base, FrameworkMethod method, Object target) {
		final ExecutorService executor = Executors.newFixedThreadPool(threads);

		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				List<Future<?>> results = Lists.newArrayList();

				for (int i = 0; i < times; i++) {
					results.add(executor.submit(new Runnable() {
						@Override
						public void run() {
							try {
								base.evaluate();
							} catch (Throwable e) {
								throw Throwables.propagate(e);
							}
						}
					}));
				}

				for (Future<?> result : results) {
					try {
						result.get();
					} catch (ExecutionException e) {
						throw e.getCause();
					}
				}
			}
		};
	}
}

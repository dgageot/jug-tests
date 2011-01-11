package rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.*;
import com.google.common.base.Service;

public class StartService<T extends Service> implements MethodRule {
	private static final int DEFAULT_PORT = 8183;

	private final Class<T> serverClass;
	private T server;
	private int port;

	private StartService(Class<T> serverClass) {
		this.serverClass = serverClass;
	}

	public static <T extends Service> StartService<T> start(Class<T> serverClass) {
		return new StartService<T>(serverClass);
	}

	@Override
	public final Statement apply(final Statement base, FrameworkMethod method, Object target) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				startService();
				try {
					base.evaluate();
				} finally {
					stopService();
				}
			}
		};
	}

	public T getService() {
		return server;
	}

	void startService() {
		for (port = DEFAULT_PORT; port < DEFAULT_PORT + 10; port++) {
			try {
				System.out.println("Trying to start service on port " + port);
				server = serverClass.getConstructor(int.class).newInstance(port);
				server.startAndWait();
				return;
			} catch (Exception e) {
				System.err.println("Unable to bind server: " + e.getMessage());
			}
		}
		throw new IllegalStateException("Unable to start server");
	}

	void stopService() {
		if (null != server) {
			server.stopAndWait();
		}
	}
}
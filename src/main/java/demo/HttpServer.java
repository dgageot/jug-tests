package demo;

import java.io.Closeable;
import com.google.common.io.Closeables;
import com.google.common.util.concurrent.AbstractService;
import com.sun.jersey.api.core.*;
import com.sun.jersey.simple.container.SimpleServerFactory;

public class HttpServer extends AbstractService {
	private final int port;
	private Closeable server;

	public HttpServer(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	@Override
	protected void doStart() {
		try {
			ResourceConfig config = new DefaultResourceConfig(Index.class);
			server = SimpleServerFactory.create("http://localhost:" + port + "/", config);

			notifyStarted();
		} catch (Throwable e) {
			notifyFailed(e);
		}
	}

	@Override
	protected void doStop() {
		Closeables.closeQuietly(server);
		notifyStopped();
	}
}

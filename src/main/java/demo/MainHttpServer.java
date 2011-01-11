package demo;

import java.io.Closeable;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.common.io.Closeables;
import com.sun.jersey.api.core.*;
import com.sun.jersey.simple.container.SimpleServerFactory;

@Path("/index.html")
public class MainHttpServer {
	private Closeable server;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "Hello, world";
	}

	public void start() throws Exception {
		ResourceConfig config = new DefaultResourceConfig(MainHttpServer.class);

		server = SimpleServerFactory.create("http://localhost:8080/", config);
	}

	public void stop() {
		Closeables.closeQuietly(server);
	}
}

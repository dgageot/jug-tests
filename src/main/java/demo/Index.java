package demo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/index.html")
public class Index {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "Hello, world";
	}
}
package junit.expert01ExternalResource;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static rules.StartService.*;
import org.junit.*;
import rules.StartService;
import demo.HttpServer;

// A @ClassRule would be better. Maybe in 4.9.
// https://github.com/KentBeck/junit/commit/b310cd56f1039389aa360fbf51c6349e7a8e1f02
//
public class HttpServerTest {
	@Rule
	public StartService<HttpServer> httpServer = start(HttpServer.class);

	@Test
	public void canGetIndex() {
		given().port(httpServer.getService().getPort()) //
				.expect().content(equalTo("Hello, world")) //
				.when().get("/index.html");
	}

	@Test
	public void failToGetAnUnkownPage() {
		given().port(httpServer.getService().getPort()) //
				.expect().statusCode(404) //
				.when().get("/unknown.html");
	}
}

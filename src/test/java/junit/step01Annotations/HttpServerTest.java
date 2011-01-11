package junit.step01Annotations;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.*;
import demo.MainHttpServer;

public class HttpServerTest {
	private static MainHttpServer httpServer = new MainHttpServer();

	@BeforeClass
	public static void startServer() throws Exception {
		System.out.println("Starting server...");
		httpServer.start();
	}

	@AfterClass
	public static void stopServer() {
		System.out.println("Stopping server...");
		httpServer.stop();
	}

	@Test
	public void canGetIndex() {
		System.out.println("Test1");

		expect().content(equalTo("Hello, world")).when().get("/index.html");
	}

	@Test
	public void failToGetAnUnkownPage() {
		System.out.println("Test2");

		expect().statusCode(404).when().get("/unknown.html");
	}
}

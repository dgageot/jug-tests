package expert02Runner;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import runners.AutoMockRunner;

@RunWith(AutoMockRunner.class)
public class MockTest {
	private Listener mockListener;

	@Test
	public void canSayHello() {
		Actor actor = new Actor();

		actor.talkTo(mockListener, "Hello");

		verify(mockListener).say("Hello");
	}

	static interface Listener {
		void say(String what);
	}

	static class Actor {
		public void talkTo(Listener listener, String what) {
			listener.say(what);
		}
	}
}

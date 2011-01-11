package junit.step06Theories;

import static org.fest.assertions.Assertions.*;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class EnumTest {
	@DataPoints
	public static ColorEnum[] colors = ColorEnum.values();

	@DataPoints
	public static String[] texts = { "red" };

	@Theory
	public void canConvertColorToText(ColorEnum color) {
		String text = TestedCode.toText(color);

		assertThat(text).isNotEmpty();
	}

	@Theory
	public void canConvertTextToColor(String text) {
		ColorEnum color = TestedCode.forText(text);

		assertThat(color).isNotNull();
	}

	private static enum ColorEnum {
		RED, GREEN, BLUE;
	}

	private static class TestedCode {
		public static String toText(ColorEnum color) {
			switch (color) {
				case RED:
					return "red";
				case GREEN:
					return "green";
				case BLUE:
					return "blue";
			}
			throw new IllegalArgumentException("Unknown color: " + color);
		}

		public static ColorEnum forText(String text) {
			return ColorEnum.valueOf(text.toUpperCase());
		}
	}
}

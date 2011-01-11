package runners;

import java.lang.reflect.Field;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;
import org.springframework.util.*;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class AutoMockRunner extends BlockJUnit4ClassRunner {
	public AutoMockRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Object createTest() throws Exception {
		final Object test = super.createTest();

		ReflectionUtils.doWithFields(test.getClass(), new FieldCallback() {
			@Override
			public void doWith(Field field) {
				if (field.getName().startsWith("mock")) {
					setFieldValue(test, field, mock(field));
				}
			}
		});

		return test;
	}

	Object mock(Field field) {
		return Mockito.mock(field.getType(), field.getName());
	}

	void setFieldValue(Object test, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(test, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Unable to access a field", e);
		}
	}
}
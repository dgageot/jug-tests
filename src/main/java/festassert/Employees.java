package festassert;

import java.util.*;
import com.google.common.collect.Lists;

public class Employees implements Iterable<String> {
	private final List<String> names = Lists.newArrayList();

	public void add(String name) {
		names.add(name);
	}

	public int count() {
		return names.size();
	}

	public String get(int index) {
		return names.get(index);
	}

	@Override
	public Iterator<String> iterator() {
		return names.iterator();
	}
}

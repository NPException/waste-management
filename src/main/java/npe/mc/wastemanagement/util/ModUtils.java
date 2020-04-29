package npe.mc.wastemanagement.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.http.util.Args;

public final class ModUtils {

	/**
	 * Inspects all public static fields of the given class, and
	 * returns a {@link Stream} with all values for fields that are
	 * type compatible with the given targetType.
	 */
	public static <T> List<T> publicStaticValues(Class<?> cls, Class<T> targetType) {
		List<T> values = new ArrayList<>();
		for (Field field : cls.getDeclaredFields()) {
			int mod = field.getModifiers();
			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)
					  && targetType.isAssignableFrom(field.getType())) {
				try {
					//noinspection unchecked
					values.add((T) field.get(null));
				} catch (IllegalAccessException e) {
					throw new RuntimeException("This should not happen", e);
				}
			}
		}
		return values;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <K, V> Map<K, V> mapOf(Object... keyvals) {
		Args.check(keyvals.length % 2 == 0, "must use even number of arguments");
		Map m = new HashMap(keyvals.length);
		for (int i = 0, size = keyvals.length; i < size; i += 2) {
			m.put(keyvals[i], keyvals[i+1]);
		}
		return m;
	}
}

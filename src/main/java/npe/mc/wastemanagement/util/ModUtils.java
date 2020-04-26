package npe.mc.wastemanagement.util;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ModUtils {

	/**
	 * Wrap a function lambda that can throw an exception to a regular {@link Function}
	 */
	public static <T, R> Function<T, R> unchecked(CheckedFunction<T, R> tf) {
		return x -> {
			try {
				return tf.apply(x);
			} catch (Exception e) {
				throw new RuntimeException("Exception in lambda", e);
			}
		};
	}

	public interface CheckedFunction<T, R> {
		R apply(T value) throws Exception;
	}

	/**
	 * Inspects all public static fields of the given class, and
	 * returns a {@link Stream} with all values for fields that are
	 * type compatible with the given targetType.
	 */
	public static <T> Stream<T> publicStaticValues(Class<?> cls, Class<T> targetType) {
		//noinspection unchecked
		return Arrays.stream(cls.getDeclaredFields())
				  .filter(f -> {
					  int mod = f.getModifiers();
					  return Modifier.isPublic(mod) && Modifier.isStatic(mod)
								 && targetType.isAssignableFrom(f.getType());
				  })
				  .map(unchecked(f -> (T) f.get(null)));
	}
}

package npe.mc.wastemanagement.util;

import java.util.function.Supplier;

import org.apache.http.util.Args;

/**
 * Delays execution of an expression and stores the value of it to be used later on.
 * This ended up being very similar to Clojure's Delay class.
 */
public final class Delay<T> implements Supplier<T> {
	private volatile Supplier<T> supplier;
	private volatile T value;

	private Delay(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	/**
	 * The supplier will be called exactly once, and store the result for later invocations.
	 *
	 * @return The lazily evaluated value that this {@link Delay} instance represents.
	 */
	@Override
	public T get() {
		Supplier<T> s = supplier;
		if (s != null) {
			synchronized (this) {
				s = supplier;
				if (s != null) {
					value = s.get();
					supplier = null;
				}
			}
		}
		return value;
	}

	public boolean isRealized() {
		return supplier == null;
	}

	/**
	 * Creates and returns a {@link Delay} instance, which will retrieve it's value
	 * on the first call to {@link #get()}. Every subsequent call to {@link #get()}
	 * will return the value that was retrieved during the first call.
	 *
	 * @param supplier
	 * 		The {@link Supplier} from which the value will be retrieved on the first call to {@link #get()}
	 */
	public static <T> Delay<T> delay(Supplier<T> supplier) {
		Args.notNull(supplier, "supplier");
		return new Delay<>(supplier);
	}
}

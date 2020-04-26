package npe.mc.wastemanagement.util;

import java.util.function.Function;

/**
 * @author jezza 29.06.2018
 */
public final class StringHelper {
	/**
	 * Formats a chunk of text, replacing defined tokens by the start and end,
	 * and passes the value off to the given function.
	 *
	 * eg:
	 *
	 * <pre>
	 * String result = formatKey("This is a {hello}", "{", "}",
	 *                           k -> Integer.toString(k.length()));
	 * // result = "This is a 5"
	 * </pre>
	 *
	 * <pre>
	 * String[] values = { "First", "Second" };
	 * String result = formatKey("This is a [0][1]", "[", "]",
	 *                           k -> values[Integer.valueOf(k)]);
	 * // result = "This is a FirstSecond"
	 * </pre>
	 *
	 * @param input
	 *           - The text to scan and alter
	 * @param startKey
	 *           - The series of characters that start the token
	 * @param endKey
	 *           - The series of characters that end the token
	 * @param transform
	 *           - The function to apply to the value found between the
	 *           startKey and the endKey
	 * @return - The formatted result.
	 */
	public static String formatToken(String input, String startKey, String endKey, Function<String, String> transform) {
		int end = 0;
		int start = input.indexOf(startKey, end);
		if (start < 0) {
			return input;
		}

		int startKeyLength = startKey.length();
		int endKeyLength = endKey.length();
		int diff;
		StringBuilder output = new StringBuilder(input.length());

		while (start >= 0) {
			diff = start - end;
			if (diff > 0) {
				if (diff == 1) {
					output.append(input.charAt(end));
				} else {
					output.append(input, end, start);
				}
			}
			if (start + startKeyLength >= input.length()) {
				throw new IllegalArgumentException("Unmatched token @ position: " + start);
			}
			end = input.indexOf(endKey, start + startKeyLength);
			if (end < 0) {
				throw new IllegalArgumentException("Unmatched token @ position: " + start);
			}
			diff = end - start - startKeyLength;
			if (diff > 0) {
				if (diff == 1) {
					output.append(transform.apply(String.valueOf(input.charAt(start + startKeyLength))));
				} else {
					output.append(transform.apply(input.substring(start + startKeyLength, end)));
				}
			} else {
				output.append(transform.apply(""));
			}
			end += endKeyLength;
			start = input.indexOf(startKey, end);
		}

		if (-1 < end && end < input.length()) {
			output.append(input, end, input.length());
		}
		return output.toString();
	}

	/**
	 * Determines the useablity of the passed CharSequence by checking for null,
	 * empty, or has non-whitespace characters.
	 *
	 * @param s
	 *            The CharSequence to be evaluated
	 * @return true if the CharSequence contains any non-whitespace character,
	 *         false otherwise.
	 */
	public static boolean useable(CharSequence s) {
		return s != null && s.length() > 0 && s.chars().anyMatch(x -> x > ' ');
	}
}

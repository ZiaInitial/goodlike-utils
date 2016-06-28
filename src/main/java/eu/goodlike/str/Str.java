package eu.goodlike.str;

import eu.goodlike.neat.Null;
import eu.goodlike.str.impl.str.StringBuilderWrapper;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * <pre>
 * Creates StringBuilderWrapper instances
 *
 * StringBuilderWrapper allows simple but neat way to construct a String using chained methods
 *
 * In general, if you feel that you need to explicitly define a variable of StringBuilderWrapper, you might as well
 * just use StringBuilder in the first place; the wrapper is intended to take care of simple cases only
 * </pre>
 */
public final class Str {

    /**
     * @return empty StringBuilderWrapper
     */
    public static StringBuilderWrapper of() {
        return new StringBuilderWrapper();
    }

    /**
     * @return StringBuilderWrapper containing all the objects, appended together
     * @throws NullPointerException if object array is null (NOT if it contains null, that is allowed)
     */
    public static StringBuilderWrapper of(Object... objects) {
        return new StringBuilderWrapper().and(objects);
    }

    /**
     * @return StringBuilderWrapper containing all the objects, appended together, using the provided StringBuilder
     * @throws NullPointerException if customBuilder or object array is null (NOT if it contains null, that is allowed)
     */
    public static StringBuilderWrapper of(StringBuilder customBuilder, Object... objects) {
        Null.check(customBuilder).ifAny("Custom builder cannot be null");
        return new StringBuilderWrapper(customBuilder).and(objects);
    }

    /**
     * @return String, formatted using the slf4j method (replace {} with given String values)
     */
    public static String format(String string, Object any) {
        return format(MessageFormatter.format(string, any));
    }

    /**
     * @return String, formatted using slf4j method (replace {}  with given String values)
     */
    public static String format(String string, Object any1, Object any2) {
        return format(MessageFormatter.format(string, any1, any2));
    }

    /**
     * @return String, formatted using slf4j method (replace {}  with given String values)
     */
    public static String format(String string, Object... any) {
        return format(MessageFormatter.arrayFormat(string, any));
    }

    // PRIVATE

    private Str() {
        throw new AssertionError("Do not instantiate, use static methods!");
    }

    private static String format(FormattingTuple formattingTuple) {
        String result = formattingTuple.getMessage();
        if (result == null)
            throw new IllegalArgumentException("Given string cannot be formatter using given object");

        return result;
    }

}
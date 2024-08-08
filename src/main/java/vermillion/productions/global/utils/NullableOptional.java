package vermillion.productions.global.utils;

import java.util.NoSuchElementException;

/**
 *
 * A classic {@link java.util.Optional} but with the possibility to be optional.
 *
 * @param <T> type of the optional.
 */
public class NullableOptional<T> {

    public static <T> NullableOptional<T> of(T value) {
        return new NullableOptional<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> NullableOptional<T> absent() {
        return (NullableOptional<T>) absent;
    }

    private static final NullableOptional<?> absent = new NullableOptional<>();

    private final T value;

    private NullableOptional() {
        this(null);
    }

    private NullableOptional(T value) {
        this.value = value;
    }

    public boolean isPresent() {
        return this != absent;
    }

    public T get() {
        if (!isPresent()) throw new NoSuchElementException();
        return value;
    }

    public T getOrDefault(T def) {
        return isPresent() ? value : def;
    }

}
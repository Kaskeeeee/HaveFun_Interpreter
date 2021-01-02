package values;

/**
 * This class represents a generic value which
 * is the result of a evaluated expression
 *
 * @param <T> type of the value
 */
public abstract class ExpValue<T> extends Value {
    protected final T value;

    public ExpValue(T value) { this.value = value; }

    public T rawValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpValue<?> expValue = (ExpValue<?>) o;
        return value.equals(expValue.value);
    }

    @Override
    public int hashCode() { return value.hashCode(); }
}

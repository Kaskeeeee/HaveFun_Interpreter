package values;

/**
 * Top level abstract class needed for the interpreter
 * It defines the necessary methods to implement
 */
public abstract class Value {
    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}

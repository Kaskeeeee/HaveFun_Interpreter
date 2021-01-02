package values;

/**
 * Class that represents commands
 */
public class ComValue extends Value {
    public final static ComValue INSTANCE = new ComValue();

    private ComValue() {}

    @Override
    public boolean equals(Object obj) { return this == obj; }

    @Override
    public int hashCode() { return 0; }
}

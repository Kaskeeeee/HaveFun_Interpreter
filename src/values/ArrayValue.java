package values;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents arrays
 */
public class ArrayValue extends ExpValue<List<ExpValue<?>>> {
    public ArrayValue(List<ExpValue<?>> value) {
        super(value);
    }

    public ExpValue<?> get(int index) {
        return value.get(index);
    }

    public void set(int index, ExpValue<?> v) {
        if (index >= value.size()) {
            for (int i = value.size(); i <= index; i++) {
                value.add(null);
            }
        }

        value.set(index, v);
    }
}

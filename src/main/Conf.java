package main;

import values.ExpValue;
import java.util.HashMap;

public class Conf {
    private final HashMap<String, ExpValue<?>> store;

    public Conf() {
        store = new HashMap<>();
    }

    public ExpValue<?> get(String id) {
        return store.get(id);
    }

    public void put(String id, ExpValue<?> v) {
        store.put(id, v);
    }
}

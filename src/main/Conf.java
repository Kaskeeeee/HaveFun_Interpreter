package main;

import java.util.HashMap;

public class Conf<T> {
    private final HashMap<String, T> store;

    public Conf() {
        store = new HashMap<>();
    }

    public T get(String id) {
        return store.get(id);
    }

    public void put(String id, T v) {
        store.put(id, v);
    }
}

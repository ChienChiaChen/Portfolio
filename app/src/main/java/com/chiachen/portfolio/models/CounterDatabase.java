package com.chiachen.portfolio.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CounterDatabase {
    private static CounterDatabase instance;

    private final Map<Integer, Counter> counters;

    private int nextId = 1;

    private CounterDatabase() {
        counters = new HashMap<>();
    }

    public static synchronized CounterDatabase getInstance() {
        if (instance == null) {
            instance = new CounterDatabase();
        }
        return instance;
    }

    public List<Counter> getAllCounters() {
        synchronized (counters) {
            return new ArrayList<>(counters.values());
        }
    }
}

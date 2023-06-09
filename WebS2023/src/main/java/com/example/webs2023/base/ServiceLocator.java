package com.example.webs2023.base;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> dependencies = new HashMap<>();

    public static <T> void registerDependency(Class<T> type, T instance) {
        if(!dependencies.containsKey(type))dependencies.put(type, instance);
    }

    public static <T> T getDependency(Class<T> type) {
        Object dependency = dependencies.get(type);
        if (dependency == null) {
            throw new RuntimeException("Dependency not found for type: " + type.getSimpleName());
        }
        return type.cast(dependency);
    }

    public static void unregisterDependency(Class<?> type) {
        dependencies.remove(type);
    }

    public static void destroy() {
        dependencies.clear();
    }
}


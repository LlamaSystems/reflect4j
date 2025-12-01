package io.github.llamasystems.reflect4j.core;

import java.util.*;

/// # ReflectorClass
///
/// @author Aliabbos
/// @since 2025-09-19
public final class ReflectorClass {

    public static List<Class<?>> getAllSuperclasses(Class<?> clazz) {
        if (clazz == null) return Collections.emptyList();
        List<Class<?>> superclasses = new ArrayList<>();
        Class<?> current = clazz.getSuperclass();
        while (current != null && current != Object.class) {
            superclasses.add(current);
            current = current.getSuperclass();
        }
        return superclasses;
    }

    public static List<Class<?>> getAllInterfaces(Class<?> clazz) {
        if (clazz == null) return Collections.emptyList();
        Set<Class<?>> interfaces = new LinkedHashSet<>();
        collectInterfaces(clazz, interfaces);
        return new ArrayList<>(interfaces);
    }

    private static void collectInterfaces(Class<?> clazz, Set<Class<?>> interfaces) {
        for (Class<?> iFace : clazz.getInterfaces()) {
            if (interfaces.add(iFace)) {
                collectInterfaces(iFace, interfaces);
            }
        }
        if (clazz.getSuperclass() != null) {
            collectInterfaces(clazz.getSuperclass(), interfaces);
        }
    }
}
package io.github.llamasystems.reflect4j.core;

import io.github.llamasystems.reflect4j.api.exception.ConstructorNotFoundException;
import io.github.llamasystems.reflect4j.commons.Params;

import java.lang.reflect.Constructor;

/// # ReflectorConstructor
///
/// @author Aliabbos
/// @since 2025-09-19
public final class ReflectorConstructor {

    public static Constructor<?> findConstructor(Class<?> clazz, Params params) {
        try {
            return clazz.getDeclaredConstructor(params.getTypes());
        } catch (NoSuchMethodException e) {
            throw new ConstructorNotFoundException(clazz, params.getTypes());
        }
    }

    public static boolean hasConstructor(Class<?> clazz, Params params) {
        try {
            findConstructor(clazz, params);
            return true;
        } catch (ConstructorNotFoundException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object... args) {
        Class<?>[] paramTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            paramTypes[i] = args[i] != null ? args[i].getClass() : Object.class;
        }
        try {
            Constructor<T> constructor = (Constructor<T>) findConstructor(clazz, Params.of(paramTypes));
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create new instance of " + clazz.getName(), e);
        }
    }
}

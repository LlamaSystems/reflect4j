package io.github.llamasystems.reflect4j.core;

import io.github.llamasystems.reflect4j.api.exception.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// # ReflectorField
///
/// @author Aliabbos
/// @since 2025-09-19
public final class ReflectorField {

    public static Field findField(Class<?> clazz, String fieldName, boolean includeSuperclasses) {
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }
            if (!includeSuperclasses) break;
            current = current.getSuperclass();
        }
        throw new FieldNotFoundException(clazz, fieldName);
    }

    public static List<Field> getAllFields(Class<?> clazz, boolean includeSuperclasses) {
        if (clazz == null) return Collections.emptyList();
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            Collections.addAll(fields, current.getDeclaredFields());
            if (!includeSuperclasses) break;
            current = current.getSuperclass();
        }
        return fields;
    }

    public static boolean hasField(Class<?> clazz, String fieldName, boolean includeSuperclasses) {
        try {
            findField(clazz, fieldName, includeSuperclasses);
            return true;
        } catch (FieldNotFoundException e) {
            return false;
        }
    }
}
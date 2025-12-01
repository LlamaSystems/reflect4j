package io.github.llamasystems.reflect4j.core;

import io.github.llamasystems.reflect4j.commons.Params;
import io.github.llamasystems.reflect4j.core.descriptor.DefaultMethodDescriptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// # ReflectorMethod
///
/// @author Aliabbos
/// @since 2025-09-19
public final class ReflectorMethod {

    public static DefaultMethodDescriptor findMethod(Class<?> clazz, String methodName) {
        return findMethod(clazz, methodName, Params.empty());
    }

    public static DefaultMethodDescriptor findMethod(Class<?> clazz, String methodName, Params params) {
        for (Class<?> current = clazz; current != null && current != Object.class; current = current.getSuperclass()) {
            try {
                Method method = current.getDeclaredMethod(methodName, params.getTypes());
                return new DefaultMethodDescriptor(method);
            } catch (NoSuchMethodException ignored) {
            }
        }
        return new DefaultMethodDescriptor(null);
    }

    public static List<Method> getAllMethods(Class<?> clazz, boolean includeInherited) {
        if (clazz == null) return Collections.emptyList();
        List<Method> methods = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            Collections.addAll(methods, current.getDeclaredMethods());
            if (!includeInherited) break;
            current = current.getSuperclass();
        }
        return methods;
    }
}
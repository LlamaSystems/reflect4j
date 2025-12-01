package io.github.llamasystems.reflect4j.core.resolver;

import io.github.llamasystems.reflect4j.api.TypeRegistry;

import java.util.List;

/// # TypeResolver
///
/// @author Aliabbos Ashurov
/// @since 24/11/25
public final class TypeResolver implements TypeRegistry {

    public static final List<TypeRegistry> TYPE_REGISTRIES = List.of();

    @Override
    public Class<?> resolveType(String typeName) {
        for (TypeRegistry typeRegistry : TYPE_REGISTRIES) {
            var clazz = typeRegistry.resolveType(typeName);
            if (clazz != null) {
                return clazz;
            }
        }
        return null;
    }
}

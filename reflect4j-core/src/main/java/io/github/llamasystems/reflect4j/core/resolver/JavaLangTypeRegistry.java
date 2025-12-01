package io.github.llamasystems.reflect4j.core.resolver;

import io.github.llamasystems.reflect4j.api.TypeRegistry;

import java.util.Map;

/// # JavaLangTypeRegistry
///
/// @author Aliabbos Ashurov
/// @since 24/11/25
final class JavaLangTypeRegistry implements TypeRegistry {

    private static final Map<String, Class<?>> map = Map.ofEntries();

    @Override
    public Class<?> resolveType(String typeName) {
        return null;
    }

    @Override
    public void registerAlias(String typeName, Class<?> clazz) {
        TypeRegistry.super.registerAlias(typeName, clazz);
    }
}

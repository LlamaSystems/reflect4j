package io.github.reflect4j.api;

import io.github.reflect4j.api.exception.UnsupportedAliasOperationException;

/// # TypeRegistry
///
/// Strategy interface for resolving type names into [Class] instances.
///
/// A `TypeRegistry` acts as a registry of aliases that can be used
/// by [io.github.reflect4j.core.TypeResolver] or other reflection utilities
/// to translate short or custom names into fully qualified Java types.
///
/// Implementations may:
///
///   - Provide fixed, immutable aliases (e.g., `PrimitiveTypeRegistry`)
///   - Expose dynamic alias registration (e.g., `JavaLangTypeRegistry`)
///   - Compose multiple registries (e.g., `CompositeTypeRegistry`)
///   - Be discovered automatically at runtime via [java.util.ServiceLoader]
///
/// ### Design Notes
///
///   - Returning `null` from [#resolveType(String)] indicates that this registry
///     cannot resolve the given type name, allowing other registries to participate in resolution.
///   - [#registerAlias(String,Class)] by default throws [UnsupportedAliasOperationException];
///     immutable registries may leave it unsupported, while mutable registries override it.
///   - Thread safety is the responsibility of the implementation. Registries that
///     support dynamic registration should typically use a concurrent map.
///   - Type name resolution is case-sensitive unless otherwise documented by the implementation.
///   - Passing `null` for `typeName` or `clazz` may throw [NullPointerException].
///
/// @author Aliabbos Ashurov
/// @since 2025-09-21
public interface TypeRegistry {

    /// Attempts to resolve the given type name into a [Class].
    /// Implementations may:
    ///
    ///   - Handle primitive names (e.g., `"int"` → `int.class`)
    ///   - Handle common `java.lang` shortcuts (e.g., `"String"` → `java.lang.String`)
    ///   - Handle application-specific aliases (e.g., `"User"` → `com.example.User`)
    ///
    /// @param typeName the short name, or primitive name; must not be `null`
    /// @return the resolved [Class], or `null` if this registry cannot resolve the given type name
    /// @throws NullPointerException if `typeName` is `null`
    Class<?> resolveType(String typeName);

    /// Registers a new alias for a class name.
    /// The default implementation throws [UnsupportedAliasOperationException],
    /// making this operation optional for registries that are immutable.
    /// Implementations that support dynamic aliasing (e.g., backed by a mutable map)
    /// should override this method.
    ///
    /// @param typeName the class name (e.g., `"UUID"`); must not be `null`
    /// @param clazz    the class to associate with the alias; must not be `null`
    /// @throws IllegalArgumentException           if the alias or class is invalid according to the implementation rules
    /// @throws UnsupportedAliasOperationException if this registry does not support dynamic aliasing
    /// @throws NullPointerException               if `typeName` or `clazz` is `null`
    default void registerAlias(String typeName, Class<?> clazz) {
        throw new UnsupportedAliasOperationException(
                getClass().getSimpleName() + " does not support dynamic aliases"
        );
    }
}

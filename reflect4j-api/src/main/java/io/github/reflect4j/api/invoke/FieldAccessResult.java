package io.github.reflect4j.api.invoke;

import java.lang.reflect.Field;

/// # FieldAccessResult
///
/// Represents the result of a field access (read or write) performed via reflection.
///
/// Provides access to the field value, any exception thrown during access,
/// and the underlying [Field] object.
///
/// @param <T> the type of the field value
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
public interface FieldAccessResult<T> extends InvocationResult<T> {

    /// Returns the [Field] object that was accessed.
    ///
    /// This allows inspection of the field metadata, including name,
    /// type, modifiers, and annotations.
    ///
    /// @return the [Field] accessed
    Field getField();
}

package io.github.reflect4j.api.invoke;

import java.lang.reflect.Method;

/// # MethodInvocationResult
///
/// Represents the result of a method invocation performed via reflection.
///
/// Provides access to the method return value, any exception thrown during
/// invocation, and the underlying [Method] object.
///
/// @param <T> the type of the method return value
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
public interface MethodInvocationResult<T> extends InvocationResult<T> {

    /// Returns the [Method] object that was invoked.
    ///
    /// This allows inspection of the method metadata, including name,
    /// parameter types, return type, modifiers, and annotations.
    ///
    /// @return the [Method] invoked
    Method getMethod();
}

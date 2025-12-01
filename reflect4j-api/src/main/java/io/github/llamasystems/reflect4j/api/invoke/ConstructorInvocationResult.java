package io.github.llamasystems.reflect4j.api.invoke;

import java.lang.reflect.Constructor;

/// # ConstructorInvocationResult
///
/// Represents the result of a constructor invocation performed via reflection.
///
/// Provides access to the instance created by the constructor, any exception
/// thrown during invocation, and the underlying [Constructor] object.
///
/// @param <T> the type of the object instantiated
///
/// @author Aliabbos Ashurov
/// @since 1.0.0
public interface ConstructorInvocationResult<T> extends InvocationResult<T> {

    /// Returns the [Constructor] object that was invoked.
    ///
    /// This allows inspection of the constructor metadata, including
    /// parameter types, modifiers, and annotations.
    ///
    /// @return the [Constructor] invoked
    Constructor<T> getConstructor();
}

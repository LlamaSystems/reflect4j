package io.github.reflect4j.api.descriptor;

import io.github.reflect4j.api.exception.ElementNotFoundException;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/// # Descriptor
///
/// Base abstraction for all descriptors in the reflection library.
///
/// A `Descriptor` wraps a reflective Java element (e.g. [java.lang.reflect.Method],
/// [java.lang.reflect.Field], [java.lang.Class], etc.)
/// and provides a uniform, type-safe API for accessing its metadata.
///
/// Descriptors are intended to be lightweight wrappers around reflection types
/// and can be freely created or discarded by clients.
///
/// @param <T>    the underlying reflective type (e.g., `Method`, `Field`, `Class`, etc.)
/// @param <SELF> the concrete descriptor subtype extending this interface, used for fluent chaining in strongly typed APIs
/// @author Aliabbos Ashurov
/// @since 1.0.0
public interface Descriptor<T, SELF extends Descriptor<T, SELF>> {

    /// Returns the simple name of the underlying element.
    ///
    ///     - For classes: the simple class name.
    ///     - For methods/fields/constructors: the declared member name.
    ///     - For annotations: the annotation type name.
    ///
    /// @return the simple name, never `null`
    String getName();

    /// Returns the unique signature of this descriptor.
    ///
    /// The signature is a stable, fully qualified textual representation
    /// of the underlying element. Its format depends on the descriptor type:
    ///
    /// - For a [ClassDescriptor]:
    ///   ```
    ///   com.example.MyClass
    ///```
    ///
    /// - For a [FieldDescriptor]:
    ///   ```
    ///   com.example.MyClass#fieldName:java.lang.String
    ///```
    ///
    /// - For a [MethodDescriptor]:
    ///   ```
    ///   com.example.MyClass#process(java.lang.String, int):void
    ///```
    ///
    /// - For a [ConstructorDescriptor]:
    ///   ```
    ///   com.example.MyClass(int, java.lang.String)
    ///```
    ///
    /// - For an [AnnotationDescriptor]:
    ///   ```
    ///   com.example.MyAnnotation(value='some')
    ///```
    ///
    /// Signatures are intended for identity, comparison, and lookup purposes.
    /// They are guaranteed to be non-`null`, but the exact formatting is
    /// implementation-defined and may evolve while preserving uniqueness.
    ///
    /// @return the unique signature of this descriptor; never `null`
    String getSignature();

    /// Returns the underlying reflection element, or `null` if absent.
    ///
    /// @return the wrapped reflection object, possibly `null`
    T unwrap();

    /// Returns an [Optional] of the underlying element.
    ///
    /// Useful for functional-style or safe access patterns.
    ///
    /// @return an `Optional` wrapping the underlying element; never `null`
    default Optional<T> asOptional() {
        return Optional.ofNullable(unwrap());
    }

    /// Returns whether the underlying element is non-null.
    ///
    /// @return `true` if present, `false` otherwise
    default boolean isPresent() {
        return unwrap() != null;
    }

    /// Executes the given consumer with this descriptor instance
    /// if the underlying element is present.
    ///
    /// @param action the action to perform if this descriptor wraps a present element;
    ///                             must not be `null`
    /// @return this descriptor for fluent chaining
    /// @throws NullPointerException if `action` is `null`
    @SuppressWarnings("unchecked")
    default SELF ifPresent(Consumer<? super SELF> action) {
        Objects.requireNonNull(action, "action must not be null");

        if (isPresent()) {
            action.accept((SELF) this);
        }
        return (SELF) this;
    }

    /// Executes the given consumer with the underlying reflection element
    /// if it is present.
    ///
    /// This method is intended for direct, element-level access — for example,
    /// to perform standard reflection operations on the wrapped object.
    ///
    /// @param action the action to perform if the underlying element is present;
    ///                             must not be `null`
    /// @return this descriptor for fluent chaining
    /// @throws NullPointerException if `action` is `null`
    @SuppressWarnings("unchecked")
    default SELF ifPresentElement(Consumer<? super T> action) {
        Objects.requireNonNull(action, "action must not be null");

        T value = unwrap();
        if (value != null) {
            action.accept(value);
        }
        return (SELF) this;
    }

    /// Ensures that the underlying element is non-null.
    ///
    /// This method should be used when you are confident that the descriptor wraps
    /// an actual reflective element. If the element is absent, an exception is thrown.
    ///
    /// @return this descriptor for fluent chaining
    /// @throws ElementNotFoundException if the underlying element is null
    @SuppressWarnings("unchecked")
    default SELF unsafe() {
        if (!isPresent()) {
            throw new ElementNotFoundException("Descriptor element is absent: " + getSignature());
        }
        return (SELF) this;
    }
}
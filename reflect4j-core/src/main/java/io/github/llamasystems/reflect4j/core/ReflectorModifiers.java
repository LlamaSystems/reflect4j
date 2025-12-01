package io.github.llamasystems.reflect4j.core;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/// # ReflectorModifiers
///
/// @author Aliabbos Ashurov
/// @since 2025-09-21
public final class ReflectorModifiers {

    /// Private constructor to prevent instantiation
    private ReflectorModifiers() {
    }

    // ------------------- Member Modifiers -------------------

    /// Checks if the member is public
    public static boolean isPublic(Member member) {
        return Modifier.isPublic(member.getModifiers());
    }

    /// Checks if the member is private
    public static boolean isPrivate(Member member) {
        return Modifier.isPrivate(member.getModifiers());
    }

    /// Checks if the member is protected
    public static boolean isProtected(Member member) {
        return Modifier.isProtected(member.getModifiers());
    }

    /// Checks if the member is static
    public static boolean isStatic(Member member) {
        return Modifier.isStatic(member.getModifiers());
    }

    /// Checks if the member is final
    public static boolean isFinal(Member member) {
        return Modifier.isFinal(member.getModifiers());
    }

    /// Checks if the member is abstract
    public static boolean isAbstract(Member member) {
        return Modifier.isAbstract(member.getModifiers());
    }

    /// Checks if the member is synchronized
    public static boolean isSynchronized(Member member) {
        return Modifier.isSynchronized(member.getModifiers());
    }

    /// Checks if the member is native
    public static boolean isNative(Member member) {
        return Modifier.isNative(member.getModifiers());
    }

    /// Checks if the member is volatile (fields only)
    public static boolean isVolatile(Member member) {
        return Modifier.isVolatile(member.getModifiers());
    }

    /// Checks if the member is transient (fields only)
    public static boolean isTransient(Member member) {
        return Modifier.isTransient(member.getModifiers());
    }

    // ------------------- Class Modifiers -------------------

    /// Checks if the class is an interface
    public static boolean isInterface(Class<?> clazz) {
        return clazz.isInterface();
    }

    /// Checks if the class is a record (Java 14+)
    public static boolean isRecord(Class<?> clazz) {
        return clazz.isRecord();
    }
}


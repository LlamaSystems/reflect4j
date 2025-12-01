package io.github.llamasystems.reflect4j.core.descriptor;

import io.github.llamasystems.reflect4j.api.descriptor.AnnotationDescriptor;
import io.github.llamasystems.reflect4j.api.descriptor.MethodDescriptor;
import io.github.llamasystems.reflect4j.api.invoke.MethodInvocationResult;
import io.github.llamasystems.reflect4j.core.ReflectorModifiers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/// # DefaultMethodDescriptor
///
/// @author Aliabbos Ashurov
/// @since 27/10/25
public class DefaultMethodDescriptor implements MethodDescriptor {

    private final Method method;

    public DefaultMethodDescriptor(Method method) {
        this.method = method;
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public List<Class<?>> getParameterTypes() {
        return List.of();
    }

    @Override
    public int getParameterCount() {
        return method.getParameterCount();
    }


    @Override
    public boolean isAbstract() {
        return ReflectorModifiers.isAbstract(method);
    }

    @Override
    public boolean isStatic() {
        return ReflectorModifiers.isStatic(method);
    }

    @Override
    public boolean isFinal() {
        return ReflectorModifiers.isFinal(method);
    }

    @Override
    public boolean isSynchronized() {
        return ReflectorModifiers.isSynchronized(method);
    }

    @Override
    public boolean isNative() {
        return ReflectorModifiers.isNative(method);
    }

    @Override
    public boolean isPublic() {
        return ReflectorModifiers.isPublic(method);
    }

    @Override
    public boolean isProtected() {
        return ReflectorModifiers.isProtected(method);
    }

    @Override
    public boolean isPrivate() {
        return ReflectorModifiers.isPrivate(method);
    }

    @Override
    public boolean isPackagePrivate() {
        return false;
    }

    @Override
    public <R> MethodInvocationResult<R> invoke(Object target, Object... args) {
        return null;
    }

    @Override
    public Class<?> getDeclaringClass() {
        return method.getDeclaringClass();
    }

    @Override
    public int getModifiers() {
        return method.getModifiers();
    }

    @Override
    public <A extends Annotation> AnnotationDescriptor<A> getAnnotation(Class<A> type) {
        return null;
    }

    @Override
    public AnnotationDescriptor<?> getAnnotation(String signature) {
        return null;
    }

    @Override
    public <A extends Annotation> boolean hasAnnotation(Class<A> type) {
        return false;
    }

    @Override
    public boolean hasAnnotation(String signature) {
        return false;
    }

    @Override
    public List<? extends AnnotationDescriptor<?>> getAnnotations() {
        return null;
    }

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public String getSignature() {
        return "";
    }

    @Override
    public Method unwrap() {
        return method;
    }
}

package io.github.llamasystems.reflect4j.core.descriptor;

import io.github.llamasystems.reflect4j.api.descriptor.AnnotationDescriptor;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/// # DefaultAnnotationDescriptor
///
/// @author Aliabbos Ashurov
/// @since 24/11/25
public class DefaultAnnotationDescriptor<T extends Annotation> implements AnnotationDescriptor<T> {

    private final T annotation;

    public DefaultAnnotationDescriptor(T annotation) {
        this.annotation = annotation;
    }

    @Override
    public String getName() {
        return annotation.annotationType().getName();
    }

    @Override
    public String getSignature() {
        return "unknown";
    }

    @Override
    public T unwrap() {
        return annotation;
    }

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return annotation.annotationType();
    }

    @Override
    public AnnotationDescriptor<?> getMetaAnnotation(String signature) {
        return null;
    }

    @Override
    public <A extends Annotation> AnnotationDescriptor<A> getMetaAnnotation(Class<A> annotationType) {
        return null;
    }

    @Override
    public List<? extends AnnotationDescriptor<?>> getMetaAnnotations() {
        return List.of();
    }

    @Override
    public boolean hasMetaAnnotation(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean hasMetaAnnotation(String signature) {
        return false;
    }

    @Override
    public <R> R attribute(String name, Class<R> type) {
        return null;
    }

    @Override
    public <R> R attribute(String name, Class<R> type, R defaultValue) {
        return null;
    }

    @Override
    public Map<String, Object> attributes() {
        return Map.of();
    }
}

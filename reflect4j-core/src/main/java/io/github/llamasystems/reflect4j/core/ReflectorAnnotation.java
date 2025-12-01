package io.github.llamasystems.reflect4j.core;

import io.github.llamasystems.reflect4j.api.exception.AnnotationNotFoundException;
import io.github.llamasystems.reflect4j.core.matcher.AnnotationSignatureMatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/// # ReflectorAnnotation
///
/// @author Aliabbos Ashurov
/// @since 2025-09-19
public final class ReflectorAnnotation {

    private static final AnnotationSignatureMatcher ANNOTATION_SIGNATURE_MATCHER = new AnnotationSignatureMatcher();

    private ReflectorAnnotation() {
    }

    public static <T extends Annotation> T findAnnotation(AnnotatedElement element, Class<T> annotationClass) {
        T annotation = element.getAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        throw new AnnotationNotFoundException(annotationClass, element);
    }

    public static <T extends Annotation> T findAnnotation(Class<?> clazz, Class<T> annotationClass) {
        Class<?> current = clazz;
        while (current != null && current != Object.class) {

            T annotation = current.getAnnotation(annotationClass);
            if (annotation != null) {
                return annotation;
            }
            current = current.getSuperclass();
        }
        throw new AnnotationNotFoundException(annotationClass, clazz);
    }

    public static boolean hasAnnotation(AnnotatedElement element, String signature) {
        var parsedSignature = ANNOTATION_SIGNATURE_MATCHER.parse(signature);
        //return hasAnnotation(element, null);
        return false;
    }

    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> annotationClass) {
        if (element == null) return false;
        return element.isAnnotationPresent(annotationClass);
    }
}

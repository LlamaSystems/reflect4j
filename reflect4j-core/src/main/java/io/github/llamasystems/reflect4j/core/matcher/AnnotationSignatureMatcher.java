package io.github.llamasystems.reflect4j.core.matcher;

import io.github.llamasystems.reflect4j.api.parser.SignatureParser;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matches and parses annotation signatures.
 *
 * @author Aliabbos Ashurov
 * @since 2025-09-19
 */
public final class AnnotationSignatureMatcher implements SignatureParser<AnnotationSignatureMatcher.ParsedAnnotationSignature> {

    /**
     * Regex pattern to match annotation signatures:
     * - Starts with '@'
     * - Fully qualified class name
     * - Optional parameters inside parentheses
     */
    private static final Pattern ANNOTATION_PATTERN =
            Pattern.compile("^@([a-zA-Z_$][a-zA-Z\\d_$.]*)(\\(([^()]*)\\))?$");

    @Override
    public boolean supports(String signature) {
        if (signature == null) return false;
        return ANNOTATION_PATTERN.matcher(signature).matches();
    }

    @Override
    public ParsedAnnotationSignature parse(String signature) {
        validate(signature);
        Matcher matcher = ANNOTATION_PATTERN.matcher(signature);
        matcher.matches();

        String annotationClass = matcher.group(1);
        String paramsStr = matcher.group(3) == null ? "" : matcher.group(3).trim();

        return new ParsedAnnotationSignature(annotationClass, parseParams(paramsStr));
    }

    /**
     * Parses annotation parameters into a map.
     * Supports:
     * - key=value
     * - shorthand form: @Anno("value") → stored under key "value"
     */
    private static Map<String, String> parseParams(String paramsStr) {
        Map<String, String> params = new LinkedHashMap<>();
        if (paramsStr.isBlank()) return params;

        String[] entries = paramsStr.split("\\s*,\\s*");
        for (String entry : entries) {
            String[] kv = entry.split("\\s*=\\s*", 2);
            if (kv.length == 2) {
                params.put(kv[0], kv[1]);
            } else {
                params.put("value", kv[0]);
            }
        }
        return params;
    }


    /**
     * Parsed result for an annotation signature.
     *
     * @param annotationClass fully qualified annotation class name
     * @param parameters      parsed key-value parameters (may be empty)
     */
    public record ParsedAnnotationSignature(String annotationClass, Map<String, String> parameters) {
    }
}
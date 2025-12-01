package io.github.llamasystems.reflect4j.core.matcher;

import io.github.llamasystems.reflect4j.api.parser.SignatureParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aliabbos Ashurov
 * @since 2025-09-19
 */
public final class MethodSignatureMatcher implements SignatureParser<MethodSignatureMatcher.ParsedMethodSignature> {

    private static final Pattern METHOD_PATTERN =
            Pattern.compile("^([a-zA-Z_$][a-zA-Z\\d_$]*)\\((.*)\\)$");

    @Override
    public boolean supports(String signature) {
        if (signature == null) return false;
        Matcher matcher = METHOD_PATTERN.matcher(signature);
        return matcher.matches();
    }

    @Override
    public ParsedMethodSignature parse(String signature) {
        validate(signature);
        Matcher matcher = METHOD_PATTERN.matcher(signature);
        matcher.matches();

        String methodName = matcher.group(1);
        String paramsStr = matcher.group(2).trim();
        String[] paramTypes = parseParameterTypes(paramsStr);

        return new ParsedMethodSignature(methodName, paramTypes);
    }

    /**
     * Utility to parse parameter types, ignoring variable names
     */
    private String[] parseParameterTypes(String paramsStr) {
        if (paramsStr.isEmpty()) return new String[0];

        String[] params = paramsStr.split("\\s*,\\s*");
        String[] types = new String[params.length];

        for (int i = 0; i < params.length; i++) {
            String[] parts = params[i].trim().split("\\s+");
            if (parts.length == 0) throw new IllegalArgumentException("Empty parameter: " + params[i]);
            types[i] = parts[0];
        }

        return types;
    }

    /**
     * Parsed result for a method signature
     */
    public record ParsedMethodSignature(String methodName, String[] parameterTypes) {
    }
}

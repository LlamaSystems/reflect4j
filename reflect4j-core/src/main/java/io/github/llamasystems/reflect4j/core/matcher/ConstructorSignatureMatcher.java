package io.github.llamasystems.reflect4j.core.matcher;

import io.github.llamasystems.reflect4j.api.parser.SignatureParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A matcher and parser for constructor signatures.
 *
 * <p>Validates and parses constructor signatures provided as parameter lists only.
 * Example: "(int _, java.lang.String anyValue)"
 */
public final class ConstructorSignatureMatcher implements SignatureParser<ConstructorSignatureMatcher.ParsedConstructorSignature> {

    private static final Pattern CONSTRUCTOR_PATTERN =
            Pattern.compile("^\\((.*)\\)$");

    @Override
    public boolean supports(String signature) {
        if (signature == null) return false;
        Matcher matcher = CONSTRUCTOR_PATTERN.matcher(signature);
        return matcher.matches();
    }

    @Override
    public ParsedConstructorSignature parse(String signature) {
        validate(signature); // throws if invalid
        Matcher matcher = CONSTRUCTOR_PATTERN.matcher(signature);
        matcher.matches();

        String paramsStr = matcher.group(1).trim();
        String[] paramTypes = parseParameterTypes(paramsStr);

        return new ParsedConstructorSignature(paramTypes);
    }

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
     * Parsed result for a constructor signature
     */
    public record ParsedConstructorSignature(String[] parameterTypes) {
    }
}

package io.github.llamasystems.reflect4j.api.parser;

import io.github.llamasystems.reflect4j.api.exception.InvalidSignatureException;

/// # SignatureParser
/// Contract for validating and parsing signature strings into strongly typed representations.
///
/// A `SignatureParser` is responsible for:
///
///     - Determining whether a given signature string is supported by this parser ([#supports(String)])
///     - Parsing a valid signature string into a strongly typed representation ([#parse(String)])
///     - Optionally enforcing validation strictly via [#validate(String)]
///
/// @param <T> the type of the parsed signature object returned by [#parse(String)]
///
/// @author Aliabbos Ashurov
/// @since 2025-09-29
public interface SignatureParser<T> {

    /// Determines whether this parser can handle the given signature string.
    ///
    /// @param signature the raw signature string (must not be `null`)
    ///
    /// @return `true` if this parser can handle the signature, `false` otherwise
    /// @throws NullPointerException if signature is null
    boolean supports(String signature);

    /// Parses the given signature into a strongly typed object.
    ///
    /// Implementations may assume the signature is valid (if already checked via [#supports(String)]),
    /// or may perform validation internally and throw an exception if invalid.
    ///
    /// @param signature the raw signature string (must not be `null`)
    ///
    /// @return the parsed representation of the signature
    /// @throws InvalidSignatureException if the signature is invalid
    /// @throws NullPointerException if signature is null
    T parse(String signature);

    /// Validates the given signature and throws an [InvalidSignatureException]
    /// if it does not conform to the expected format.
    ///
    /// @param signature the raw signature string (must not be `null`)
    ///
    /// @throws InvalidSignatureException if the signature is invalid
    /// @throws NullPointerException if signature is null
    default void validate(String signature) {
        if (!supports(signature)) {
            throw new InvalidSignatureException("Invalid signature: " + signature);
        }
    }
}

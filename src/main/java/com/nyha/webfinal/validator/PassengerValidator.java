package com.nyha.webfinal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates passenger info
 *
 * @author Dovlet Jumaev
 */
public class PassengerValidator {
    private static final Pattern NAME_REGEX = Pattern
            .compile("\\w{3,40}");
    private static final Pattern NUMBER_REGEX = Pattern
            .compile("\\w{3,15}");


    private PassengerValidator() {
    }

    /**
     * Checks if name is valid
     *
     * @param name {@link String}
     * @return boolean true if name is valid, else false
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        Matcher matcher = NAME_REGEX.matcher(name);
        return matcher.matches();
    }

    /**
     * Checks if number is valid
     *
     * @param number {@link String}
     * @return boolean true if number is valid, else false
     */
    public static boolean isValidNumber(String number) {
        if (number == null || number.isBlank()) {
            return false;
        }
        Matcher matcher = NUMBER_REGEX.matcher(number);
        return matcher.matches();
    }
}

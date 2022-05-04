package com.nyha.webfinal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates bank account info
 *
 * @author Dovlet Jumaev
 */
public class BankValidator {
    private static final Pattern ACCOUNT_NUMBER_REGEX = Pattern
            .compile("\\d{1,20}");

    private BankValidator() {
    }

    /**
     * Checks if account number is valid
     *
     * @param accountNumber {@link String}
     * @return boolean true if account number is valid, else false
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            return false;
        }
        Matcher matcher = ACCOUNT_NUMBER_REGEX.matcher(accountNumber);
        return matcher.matches();
    }
}

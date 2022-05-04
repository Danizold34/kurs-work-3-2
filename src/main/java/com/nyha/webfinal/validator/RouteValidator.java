package com.nyha.webfinal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates route info
 *
 * @author Dovlet Jumaev
 */
public class RouteValidator {
    private static final Pattern STATION_REGEX = Pattern
            .compile("\\w{3,40}");


    private RouteValidator() {
    }

    /**
     * Checks if station is valid
     *
     * @param station {@link String}
     * @return boolean true if station is valid, else false
     */
    public static boolean isValidStation(String station) {
        if (station == null || station.isBlank()) {
            return false;
        }
        Matcher matcher = STATION_REGEX.matcher(station);
        return matcher.matches();
    }
}
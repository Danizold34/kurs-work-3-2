package com.nyha.webfinal.model.dao;

/**
 * Describes all column name
 *
 * @author Dovlet Jumaev
 */
public class ColumnName {

    //Constants for users table
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_ROLE = "role";
    public static final String USER_USERNAME = "username";

    //Constants for passengers table
    public static final String PASSENGER_ID = "passenger_id";
    public static final String PASSENGER_FIRST_NAME = "first_name";
    public static final String PASSENGER_LAST_NAME = "last_name";
    public static final String PASSENGER_PASSPORT_NUMBER = "passport_number";
    public static final String PASSENGER_PHONE_NUMBER = "phone_number";

    //Constants for tickets table
    public static final String TICKET_ID = "ticket_id";
    public static final String TICKET_DEPARTURE_STATION = "departure_station";
    public static final String TICKET_ARRIVAL_STATION = "arrival_station";
    public static final String TICKET_SEAT = "seat";
    public static final String TICKET_PRICE = "ticket_price";
    public static final String TICKET_DEPARTURE_DATE = "departure_date";
    public static final String TICKET_ARRIVAL_DATE = "arrival_date";


    //Constants for trains table
    public static final String TRAIN_ID = "train_id";
    public static final String TRAIN_NUMBER_OF_SEATS = "number_of_seats";

    //Constants for routes table
    public static final String ROUTE_ID = "route_id";
    public static final String ROUTE_STATION = "station";
    public static final String ROUTE_TIME = "time";
    public static final String ROUTE_TRAIN_NUMBER = "train_id";
    public static final String ROUTE_PRICE = "price";

    //Constants for bank_accounts table
    public static final String MONEY_AMOUNT = "money_amount";

    private ColumnName() {
    }
}

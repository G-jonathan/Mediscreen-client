package com.openclassroomsProject.Mediscreenclient.constants;

/**
 * Constants used for validation of object received by the controller.
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
public class ValidationConstants {
    public static final String NOT_NULL = "Can't be null";
    public static final String NOT_BLANK = "Can't be empty or null";
    public static final String WRONG_FORMAT = "Wrong format";
    public static final String REGEX_PHONE_NUMBER = "^[0-9]{3}?-?[0-9]{3}-?[0-9]{4}$|^$";
}
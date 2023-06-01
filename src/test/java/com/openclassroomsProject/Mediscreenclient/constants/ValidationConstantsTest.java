package com.openclassroomsProject.Mediscreenclient.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Units test for {@link ValidationConstants} class
 *
 * @author jonathan GOUVEIA
 * @version 1.0
 */
class ValidationConstantsTest {
    @Test
    void notNull_shouldReturnNotNullMessage() {
        String message = ValidationConstants.NOT_NULL;
        assertEquals("Can't be null", message);
    }

    @Test
    void wrongFormat_shouldReturnWrongFormatMessage() {
        String message = ValidationConstants.WRONG_FORMAT;
        assertEquals("Wrong format", message);
    }

    @Test
    void regexPhoneNumber_shouldReturnPhoneNumberRegexPattern() {
        String regex = ValidationConstants.REGEX_PHONE_NUMBER;
        assertEquals("^[0-9]{3}?-?[0-9]{3}-?[0-9]{4}$|^$", regex);
    }
}
package com.exceptions;

public class ErrorAssertion extends AssertionError {
    public static final String THE_CODES_DO_NOT_MATCH = "The last response are not equal to the status code expected";
    public static final String THE_MESSAGE_DO_NOT_MATCH = "The message do not match";
    public static final String INVALID_DATA = "INVALID_DATA";
    public static final String NOCATIMG_RESPONSE = "Error: Classifcation failed: correct animal not found.";

}

package com.arch.utils;

public class ValidationUtil {

    public static String validateEmptyField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value;
    }
}

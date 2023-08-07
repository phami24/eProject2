package com.example.gymmanagement.validation;

import java.util.regex.Pattern;

public class ValidateMember {

    public static boolean isValidFirstName(String firstName) {
        return !isEmpty(firstName);
    }

    public static boolean isValidLastName(String lastName) {
        return !isEmpty(lastName);
    }

    public static boolean isValidDate(String date) {
        // You can add more sophisticated date validation logic here
        return !isEmpty(date);
    }

    public static boolean isValidGender(String gender) {
        return !isEmpty(gender);
    }

    public static boolean isValidEmail(String email) {
        return !isEmpty(email) && isValidEmailFormat(email);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return !isEmpty(phoneNumber) && isValidPhoneNumberFormat(phoneNumber);
    }

    public static boolean isValidAddress(String address) {
        return !isEmpty(address);
    }

    public static boolean isValidMembershipStatus(String statusName) {
        return !isEmpty(statusName);
    }

    public static boolean isValidMembershipType(String typeName) {
        return !isEmpty(typeName);
    }

    public static boolean isValidInstructor(String instructorName) {
        return !isEmpty(instructorName);
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean isValidEmailFormat(String email) {
        // Simple email validation using regular expression
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    private static boolean isValidPhoneNumberFormat(String phoneNumber) {
        // Simple phone number validation
        return phoneNumber.matches("\\d{10}");
    }
}

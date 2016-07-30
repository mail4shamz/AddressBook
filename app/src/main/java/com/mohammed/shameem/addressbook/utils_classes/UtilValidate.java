package com.mohammed.shameem.addressbook.utils_classes;

import java.util.regex.Pattern;

/**
 * Created by shameem on 24/6/16.
 */
public class UtilValidate {

    public static boolean isValidEmail(String email) {

        final Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
        try {
            return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        } catch (NullPointerException exception) {
            return false;
        }

    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        final Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("[0-9]{8,13}");
        try {
            return MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
        } catch (NullPointerException exception) {
            return false;
        }
    }

    public static boolean isValidPassword(String Password) {
        final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9]{1,15}");

        try {
            return PASSWORD_PATTERN.matcher(Password).matches();
        } catch (NullPointerException exception) {
            return false;
        }
    }

    public static boolean isValidName(String firstName) {
        final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z\\s]{1,15}");
        try {
            return NAME_PATTERN.matcher(firstName).matches();
        } catch (NullPointerException exception) {
            return false;
        }
    }
}

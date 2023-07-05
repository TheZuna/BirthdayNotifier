package hr.TheZuna.projekt.util;

import hr.TheZuna.projekt.iznimke.IncorrectPhoneNumberException;
import hr.TheZuna.projekt.iznimke.NotAnEmailExeption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    static String PHONE_NUMBER_PATTERN = "\\+\\d{1,3}\\d{7,12}";

    public static boolean isValidNumber(String phoneNumber) throws IncorrectPhoneNumberException {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IncorrectPhoneNumberException(phoneNumber);
        }
        return true;

    }
}

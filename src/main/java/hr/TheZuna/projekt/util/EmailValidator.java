package hr.TheZuna.projekt.util;

import hr.TheZuna.projekt.iznimke.NotAnEmailExeption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface EmailValidator {
    String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) throws NotAnEmailExeption {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new NotAnEmailExeption(email);
        }
        return true;
    }
}

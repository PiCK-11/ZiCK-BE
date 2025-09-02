package com.pick.zick.domain.auth.policy;

import java.util.regex.Pattern;

public final class PasswordPolicy {
    private PasswordPolicy(){}

    private static final Pattern LETTER  = Pattern.compile(".*[A-Za-z].*");
    private static final Pattern DIGIT   = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL = Pattern.compile(".*[!@#$%^&*()\\-_=+<>?].*");

    public static boolean isValid(String pw){
        if (pw == null) return false;
        if (pw.length() < 8 || pw.length() > 64) return false;
        int kinds = 0;
        if (LETTER.matcher(pw).matches())  kinds++;
        if (DIGIT.matcher(pw).matches())   kinds++;
        if (SPECIAL.matcher(pw).matches()) kinds++;
        return kinds >= 2;
    }
}
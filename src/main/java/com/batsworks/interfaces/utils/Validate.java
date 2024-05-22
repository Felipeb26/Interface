package com.batsworks.interfaces.utils;

import javafx.scene.control.Label;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    private Validate() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean textField(RegexPattern pattern, String text, Label label) {
        Matcher matcher = Pattern.compile(pattern.getCode()).matcher(text);
        label.setVisible(!matcher.matches());
        return matcher.matches();
    }

    public static boolean textField(RegexPattern pattern, String text, String confirmText, Label label) {
        Matcher matcher = Pattern.compile(pattern.getCode()).matcher(confirmText);
        label.setVisible(!matcher.matches());
        return matcher.matches() && confirmText.equals(text);
    }
}

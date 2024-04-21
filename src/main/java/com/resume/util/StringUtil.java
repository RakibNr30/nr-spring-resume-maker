package com.resume.util;

public class StringUtil {
    public static int toInt(String text) {
        if (text.matches("\\d+")) {
            return Integer.parseInt(text);
        }

        return 0;
    }
}

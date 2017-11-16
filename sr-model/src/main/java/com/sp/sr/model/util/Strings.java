package com.sp.sr.model.util;

import java.util.Arrays;

public class Strings {
    public  static String concat(String[] strs, String concat) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : strs) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(concat);
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static String orderAsc(String answer, String concat) {
        String[] answers = answer.split(concat);
        Arrays.sort(answers);
        return concat(answers, concat);
    }
}

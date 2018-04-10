package com.sp.sr.model.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author zhoulin
 */
public class Hashes {
    public static String sha1(String text) {
        return Hashing.sha1()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }

    public static String md5(String text) {
        return Hashing.md5()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }
}

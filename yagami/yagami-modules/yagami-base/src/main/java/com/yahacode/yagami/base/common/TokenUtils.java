package com.yahacode.yagami.base.common;

import java.util.UUID;

/**
 * @author zengyongli 2018-03-01
 */
public class TokenUtils {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}

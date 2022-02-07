package com.yahacode.yagami.base.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author zengyongli
 */
public class StringUtils extends org.springframework.util.StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为null，空字符串
     *
     * @param str
     *         目标字符串
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 对字符串进行md5加密
     *
     * @param password
     *         目标字符串
     * @return 经md5加密后的字符串
     */
    public static String encryptMD5(String password) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            byte[] passwordInput = password.getBytes();
            mdInst.update(passwordInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            logger.error("字符串加密异常", e);
        }
        return null;
    }

    /**
     * generate a UUID string with 36 length
     *
     * @return UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}

package com.yahacode.yagami.base.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zengyongli 2018-02-23
 */
public class LogUtils {

    private static StackTraceElement findCaller() {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (null == callStack) {
            return null;
        }
        StackTraceElement caller = null;
        boolean foundLogClass = false;
        for (StackTraceElement element : callStack) {
            if (LogUtils.class.getName().equals(element.getClassName())) {
                foundLogClass = true;
                continue;
            }
            if (foundLogClass && !LogUtils.class.getName().equals(element.getClassName())) {
                foundLogClass = false;
                caller = element;
                break;
            }
        }
        return caller;
    }

    private static Logger logger() {
        StackTraceElement caller = findCaller();
        if (null == caller) {
            return LoggerFactory.getLogger(LogUtils.class);
        }
        return LoggerFactory.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller
                .getLineNumber());
    }

    public static void trace(String msg) {
        logger().trace(msg);
    }

    public static void trace(String format, Object... arguments) {
        logger().trace(format, arguments);
    }

    public static void trace(String msg, Throwable e) {
        logger().trace(msg, e);
    }

    public static void debug(String msg) {
        logger().debug(msg);
    }

    public static void debug(String format, Object... arguments) {
        logger().debug(format, arguments);
    }

    public static void debug(String msg, Throwable e) {
        logger().debug(msg, e);
    }

    public static void info(String msg) {
        logger().info(msg);
    }

    public static void info(String format, Object... arguments) {
        logger().info(format, arguments);
    }

    public static void info(String msg, Throwable e) {
        logger().info(msg, e);
    }

    public static void warn(String msg) {
        logger().warn(msg);
    }

    public static void warn(String format, Object... arguments) {
        logger().warn(format, arguments);
    }

    public static void warn(String msg, Throwable e) {
        logger().warn(msg, e);
    }

    public static void error(String msg) {
        logger().error(msg);
    }

    public static void error(String format, Object... arguments) {
        logger().error(format, arguments);
    }

    public static void error(String msg, Throwable e) {
        logger().error(msg, e);
    }
}

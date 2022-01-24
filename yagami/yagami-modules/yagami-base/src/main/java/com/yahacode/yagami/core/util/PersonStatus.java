package com.yahacode.yagami.core.util;

/**
 * person entity status, include descriptions in Chinese
 *
 * @author zengyongli
 * @since 2022-01-24
 */
public enum PersonStatus {

    INVALID("失效"), NORMAL("正常"), LOCKED("锁定"), UNCHECK("未知");

    /**
     * status Chinese description
     */
    private String desc;

    PersonStatus(String desc) {
        this.desc = desc;
    }

}

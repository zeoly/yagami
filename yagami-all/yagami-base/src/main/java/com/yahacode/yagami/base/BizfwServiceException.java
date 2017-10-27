package com.yahacode.yagami.base;

import java.text.MessageFormat;

import com.yahacode.yagami.base.common.PropertiesUtils;

/**
 * framework exception for business service. if throw this exception, an advice will convert it to YagamiResponse
 *
 * @author zengyongli
 */
public class BizfwServiceException extends Exception {

    private static final long serialVersionUID = -3233376165570816079L;

    /**
     * error code defined in ErrorCode
     */
    private String errorCode;

    /**
     * error message to show
     */
    private String errorMsg;

    public BizfwServiceException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = PropertiesUtils.getErrorMsg(errorCode);
    }

    public BizfwServiceException(String errorCode, Object... param) {
        super(errorCode);
        this.errorCode = errorCode;
        String msg = PropertiesUtils.getErrorMsg(errorCode);
        this.errorMsg = MessageFormat.format(msg, param);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

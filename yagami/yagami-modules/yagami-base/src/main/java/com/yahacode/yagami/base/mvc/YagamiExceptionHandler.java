//package com.yahacode.yagami.base.mvc;
//
//import com.yahacode.yagami.base.ServiceException;
//import com.yahacode.yagami.base.YagamiResponse;
//import com.yahacode.yagami.base.common.LogUtils;
//import com.yahacode.yagami.base.common.PropertiesUtils;
//import com.yahacode.yagami.base.consts.ErrorCode;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * convert framework business exception to YagamiResponse
// *
// * @author zengyongli
// */
//@ControllerAdvice
//public class YagamiExceptionHandler {
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseEntity<YagamiResponse> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        YagamiResponse response = new YagamiResponse();
//        if (e instanceof ServiceException) {
//            ServiceException serviceException = (ServiceException) e;
//            response.setCode(serviceException.getErrorCode());
//            response.setMsg(serviceException.getErrorMsg());
//        } else {
//            LogUtils.error("none biz error", e);
//            response.setCode(ErrorCode.DEFAULT_ERROR);
//            response.setMsg(PropertiesUtils.getErrorMsg(ErrorCode.DEFAULT_ERROR));
//        }
//        return new ResponseEntity<YagamiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}

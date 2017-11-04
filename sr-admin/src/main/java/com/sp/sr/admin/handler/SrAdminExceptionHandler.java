package com.sp.sr.admin.handler;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.VO.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhoulin
 */
@ControllerAdvice
public class SrAdminExceptionHandler {

    @ExceptionHandler(value = SrAdminException.class)
    @ResponseBody
    public ResultVO<Object> handlerAuthorizeException(SrAdminException e) {
        return ResultVO.error(e.getResultStatus().getCode(),e.getResultStatus().getMessage());
    }
}

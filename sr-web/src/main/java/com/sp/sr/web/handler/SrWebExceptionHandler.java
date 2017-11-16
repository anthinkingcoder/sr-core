package com.sp.sr.web.handler;

import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.web.SrWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhoulin
 */
@ControllerAdvice
@Slf4j
public class SrWebExceptionHandler {

    @ExceptionHandler(value = SrWebException.class)
    @ResponseBody
    public ResultVO<Object> handlerAuthorizeException(SrWebException e) {
        return ResultVO.error(e.getResultStatus().getCode(), e.getResultStatus().getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody

    public ResultVO<Object> handlerException(Exception e) {
        log.error("handlerException,exception = {}",e.getMessage());
        return ResultVO.error(ResultStatus.SERVICE_ERROR.getCode(), ResultStatus.SERVICE_ERROR.getMessage());
    }
}

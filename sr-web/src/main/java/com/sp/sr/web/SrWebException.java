package com.sp.sr.web;

import com.sp.sr.model.enums.ResultStatus;
import lombok.Data;

/**
 * @author zhoulin
 */
@Data
public class SrWebException extends RuntimeException {
    private ResultStatus resultStatus;

    public SrWebException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public SrWebException() {
    }

    public SrWebException(String message) {
        super(message);
    }

    public SrWebException(Throwable cause) {
        super(cause);
    }
}

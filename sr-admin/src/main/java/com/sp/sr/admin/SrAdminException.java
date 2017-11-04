package com.sp.sr.admin;

import com.sp.sr.model.enums.ResultStatus;
import lombok.Data;

/**
 * @author zhoulin
 */
@Data
public class SrAdminException extends RuntimeException {
    private ResultStatus resultStatus;

    public SrAdminException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public SrAdminException() {
    }

    public SrAdminException(String message) {
        super(message);
    }

    public SrAdminException(Throwable cause) {
        super(cause);
    }
}

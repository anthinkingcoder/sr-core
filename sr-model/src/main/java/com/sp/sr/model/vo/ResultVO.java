package com.sp.sr.model.vo;

import com.sp.sr.model.enums.ResultStatus;
import lombok.Data;

/**
 * @author zhoulin
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO() {
    }

    public static <T> ResultVO<T> ok(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(data);
        resultVO.setCode(ResultStatus.SUCCESS.getCode());
        resultVO.setMsg("SUCCESS");
        return resultVO;
    }

    public static ResultVO<Object> ok() {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData("");
        resultVO.setCode(ResultStatus.SUCCESS.getCode());
        resultVO.setMsg("SUCCESS");
        return resultVO;
    }

    @SuppressWarnings("unchecked")
    public static <Object> ResultVO<Object> error(Integer code, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData((Object) "ERROR");
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}

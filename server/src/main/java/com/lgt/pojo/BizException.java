package com.lgt.pojo;

import com.lgt.utils.ResultCode;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 8451705645719865678L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public BizException() {
        super();
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    public BizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    public BizException(String message) {
        super(message);
        setCode(-1);
        this.message = message;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

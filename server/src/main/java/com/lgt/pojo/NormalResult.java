package com.lgt.pojo;

import com.lgt.utils.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "返回结果实体类", description = "结果实体类")
public class NormalResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    private NormalResult() {

    }

    public NormalResult(ResultCode resultCode, Object data) {
        code = resultCode.getCode();
        message = resultCode.getMessage();
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    // 返回成功
    public static NormalResult success() {
        NormalResult result = new NormalResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    // 返回成功
    public static NormalResult success(Object data) {
        NormalResult result = new NormalResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    // 返回失败
    public static NormalResult fail(Integer code, String message) {
        NormalResult result = new NormalResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 返回失败
    public static NormalResult fail(ResultCode resultCode) {
        NormalResult result = new NormalResult();
        result.setResultCode(resultCode);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
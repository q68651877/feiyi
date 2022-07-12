package com.example.mqttfactorydemo.domain;

import com.example.mqttfactorydemo.util.ApiResultCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zrkc
 */
public class ApiBaseResult {

    @ApiModelProperty(value = "返回的状态码")
    protected int code = 200;

    @ApiModelProperty(value = "返回的状态消息")
    protected String message = "SUCCESS";

    public int getCode() {
        return code;
    }

    public ApiBaseResult() {

    }

    public ApiBaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiBaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public void setErrorResult(ApiResultCode resultCode) {
        this.setCode(resultCode.getCode()).setMessage(resultCode.getMsg());
    }
}

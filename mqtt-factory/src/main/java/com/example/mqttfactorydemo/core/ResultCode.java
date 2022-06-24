package com.example.mqttfactorydemo.core;

/**
 * @author peiyuxiang
 * @date 2022/6/20
 */
public enum ResultCode {
    SUCCESS(200, "sucess"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证（签名错误）"),
    NOT_FOUND(404, "接口不存在"),
    WX_UNLAWED_LOGIN(702, "非法登录"),
    USER_NOT_EXIST(950, "用户不存在"),
    USER_EXIST(800, "账号已存在"),
    SYSTEM_ERROR(810, "系统错误"),
    VERIFY_CODE_ERROR(900, "手机号或者短信验证码错误"),
    VERIFY_CODE_USED(901, "验证码已被使用"),
    VERIFY_CODE_EXPIRED(902, "验证码过期"),
    USER_PHONE_USED(903, "该手机号已被注册过"),
    VERIFY_CODE_EXCEED_MAX_TIME(904, "发送验证码超过当日发送次数"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    VALIDATE_TOKEN_FAILED(999, "登录信息已失效"),
    USER_DELETE(801, "用户已注销"),
    URL_EXIST(905, "URL重复"),
    USER_IS_EXIST(9999,"该用户名已存在"),
    DATA_NOT_EXIST(905, "数据不存在"),
    ENTITY_EXIST(906, "企业已存在"),
    DEFAULT_FUNCTION_NOT_EXIST(907, "默认功能不存在"),
    DEFAULT_FUNCTION_PERMISSION_NOT_EXIST(908, "默认功能权限不存在"),
    PROJECT_USER_IS_EXIST(909, "项目用户已存在"),
    PROJECT_USER_NOT_EXIST(910, "项目用户不存在"),
    FUNCTION_NOT_EXIST(911, "功能列表不存在"),
    ROLE_EXIST(912, "权限已存在"),
    ROLE_NOT_EXIST(913, "权限不存在"),
    ROLE_USER_EXIST(914, "权限已有用户关联"),
    EMAIL_CODE_FAIL(1903, "邮箱验证码发送失败"),
    EMAIL_EXIST(1800,"该邮箱已存在"),
    USER_NOT_entity(915, "用户无关联企业"),
    ROLE_NOT_UPDATE(916, "权限无法被编辑"),
    ROLE_NOT_DELETE(917, "权限无法被删除"),
    USER_ENTITY_EXIST(810, "用户已存在"),
    INVITE_EMAIL_FAIL(1905, "邀请邮件发送失败")
    ;

    private Integer code;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ResultCode(int code) {
        this.code = Integer.valueOf(code);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    private ResultCode(int code, String msg) {
        this.code = Integer.valueOf(code);
        this.msg = msg;
    }
}

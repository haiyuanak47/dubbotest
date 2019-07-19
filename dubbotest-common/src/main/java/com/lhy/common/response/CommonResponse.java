package com.lhy.common.response;

/**
 * @Author: Chenjianyu
 * @Date: 2019/1/21 1:09 PM
 * @Description 公共的响应消息类
 */
public class CommonResponse<T> {

    private static final String CODE_SUCCESS = "200";

    private static final String CODE_FAIL = "201";

    private static final String CODE_ERROR = "500";

    private static final String CODE_NO_LOGIN = "300";

    private static final String MSG_SUCCESS = "success";

    private static final String MSG_FAIL = "fail";

    private static final boolean TRUE_COMPLETE = true;

    private static final boolean FALSE_COMPLETE = false;

    /**
     * 是否完成
     */
    private boolean isComplete;

    /**
     * 响应状态码
     */
    private String responseCode = "";

    /**
     * 响应消息内容
     */
    private String responseMsg = "";

    /**
     * 数据内容
     */
    private T data;

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResponse() {

    }

    public CommonResponse(boolean isComplete, String responseCode, String responseMsg, T data) {
        this.setComplete(isComplete);
        this.setResponseCode(responseCode);
        this.setResponseMsg(responseMsg);
        this.setData(data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>(TRUE_COMPLETE, CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(TRUE_COMPLETE, CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static <T> CommonResponse<T> success(String code, String msg) {
        return new CommonResponse<T>(TRUE_COMPLETE, code, msg, null);
    }

    public static <T> CommonResponse<T> success(String code, String msg, T data) {
        return new CommonResponse<T>(TRUE_COMPLETE, code, msg, data);
    }

    public static <T> CommonResponse<T> fail() {
        return new CommonResponse<T>(FALSE_COMPLETE, CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> CommonResponse<T> fail(String msg) {
        return new CommonResponse<T>(FALSE_COMPLETE, CODE_FAIL, msg, null);
    }

    public static <T> CommonResponse<T> fail(T data) {
        return new CommonResponse<T>(FALSE_COMPLETE, CODE_FAIL, MSG_FAIL, data);
    }

    public static <T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<T>(FALSE_COMPLETE, code, msg, null);
    }

    public static <T> CommonResponse<T> fail(String code, String msg, T data) {
        return new CommonResponse<T>(FALSE_COMPLETE, code, msg, data);
    }

}

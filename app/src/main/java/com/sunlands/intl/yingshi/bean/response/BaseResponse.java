package com.sunlands.intl.yingshi.bean.response;


/**
 * 文件名: BaseResponse
 * 描    述: [数据基类]
 * 创建人: duzzi
 * 创建时间: 2018/6/5
 */
public class BaseResponse {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

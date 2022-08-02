package com.example.controller.utils;

import lombok.Data;

/**
 * 统一消息返回Result类
 */
@Data
public class R {
    private Boolean flag; //操作标识：true-成功；false-失败
    private Object data; //数据体
    private String msg; //提示消息（实际项目场景中，还会有 statusCode，即：响应码/状态码。

    public R() {
    }

    public R(Boolean flag) {
    }

    public R(String msg) {
        this.flag = false;
        this.msg = msg;
    }

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }
}

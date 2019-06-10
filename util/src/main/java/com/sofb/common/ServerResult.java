package com.sofb.common;


import com.sofb.enums.ServerResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ServerResult implements Serializable {

    /**
     * 状态
     */
    private ServerResultCodeEnum code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 是否成功
     */
    private boolean success;

    public ServerResult() {
    }

    public ServerResult success(Object data) {
        this.success = true;
        this.code = ServerResultCodeEnum.C0000;
        this.data = data;
        return this;
    }

    public ServerResult success(Object data, String message) {
        this.success = true;
        this.code = ServerResultCodeEnum.C0000;
        this.data = data;
        this.message = message;
        return this;
    }

    public ServerResult error(ServerResultCodeEnum code) {
        this.success = false;
        this.code = code;
        this.message = code == null ? null : code.getDesc();
        return this;
    }


    public ServerResult error(ServerResultCodeEnum code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
        return this;
    }

}

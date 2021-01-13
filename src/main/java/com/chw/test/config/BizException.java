package com.chw.test.config;

import lombok.Data;

@Data
public class BizException extends RuntimeException {

    public static final String ID = "id";
    public static final String MSG = "msg";
    private static final long serialVersionUID = -7270482454463263846L;

    private int code = 0;
    private String msg = "ok";
    private Object data;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
        this.code = 1;
        this.msg = message;
        this.data = null;
    }

    public BizException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setError(int code) {
        setError(code, "fail");
    }

    public void setError(int code, String errmsg) {
        this.code = code;
        this.msg = errmsg;
    }

}

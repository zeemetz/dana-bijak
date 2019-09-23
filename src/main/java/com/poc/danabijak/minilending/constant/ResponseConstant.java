package com.poc.danabijak.minilending.constant;

public class ResponseConstant {
    private ResponseConstant() {
        throw new IllegalStateException("constant class should not be instantiate");
    }
    public static final String SUCCESS_CODE = "success";
    public static final String INTERNAL_SERVER_ERROR = "internal_server_error";
}

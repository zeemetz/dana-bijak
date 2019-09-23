package com.poc.danabijak.minilending.controller.response;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {
    private String code;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

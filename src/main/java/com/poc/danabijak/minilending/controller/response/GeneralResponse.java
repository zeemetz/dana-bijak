package com.poc.minilending.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralResponse<T> implements Serializable {
    private String code;
    private T data;
}

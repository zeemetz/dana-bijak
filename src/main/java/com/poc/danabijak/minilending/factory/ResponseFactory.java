package com.poc.minilending.factory;

import com.poc.minilending.constant.ResponseConstant;
import com.poc.minilending.controller.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public ResponseEntity success(Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        responseObject.setCode(ResponseConstant.SUCCESS_CODE);
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity error(HttpStatus httpStatus, String errorCode) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode);
        return new ResponseEntity(responseObject, httpStatus);
    }
}

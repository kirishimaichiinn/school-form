package com.example.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class RestBean{
    public static <T> ResponseEntity<RestData<T>> success(T data) {
        return new ResponseEntity<>(new RestData<>(data,"success"), HttpStatus.valueOf(200));
    }

    public static <T> ResponseEntity<RestData<T>> success(T data, String msg) {
        return new ResponseEntity<>(new RestData<>(data,msg), HttpStatus.valueOf(200));
    }

    public static <T> ResponseEntity<RestData<T>> failure(int code, String msg) {
        return new ResponseEntity<>(new RestData<>(null,msg), HttpStatus.valueOf(code));
    }

    public static <T> ResponseEntity<RestData<T>> failure(int code) {
        return new ResponseEntity<>(new RestData<>(null,"failure"), HttpStatus.valueOf(code));
    }

    public record RestData<T>(T data, String msg) {}
}

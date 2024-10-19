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

    public static <T> ResponseEntity<RestData<T>> failure(String msg) {
        return new ResponseEntity<>(new RestData<>(null,msg), HttpStatus.valueOf(202));
    }

    public record RestData<T>(T data, String msg) {}
}

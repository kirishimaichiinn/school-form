package com.example.controller.auth;

import com.example.Util.RestBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/checkMe")
    public ResponseEntity<RestBean.RestData<String>> checkMe() {
        return RestBean.success("OK");

    }
}

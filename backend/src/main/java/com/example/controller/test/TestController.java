package com.example.controller.test;

import com.example.Util.RestBean;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Resource
    PasswordEncoder passwordEncoder;

    @PostMapping("/test")
    public ResponseEntity<RestBean.RestData<String>> test(@RequestParam("password") String psw) {
        String encoded = passwordEncoder.encode(psw);
        return RestBean.success(encoded);
    }

    @PostMapping("/testReturn")
    public ResponseEntity<RestBean.RestData<String>> testReturn(){
        //return RestBean.success("123","456");
        return RestBean.failure(203,"nonnon");
    }
}

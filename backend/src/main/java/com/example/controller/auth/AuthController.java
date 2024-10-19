package com.example.controller.auth;

import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.service.auth.AccountService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<RestBean.RestData<Object>> register(@RequestParam("nickname") String nickname, @RequestParam("account") String account, @RequestParam("password") String password) {
        if(nickname == null || account == null || password == null) return RestBean.failure("缺少参数");
        Account account_entity = new Account();
        account_entity.setNickname(nickname);
        account_entity.setAccount(account);
        account_entity.setPassword(password);

        return accountService.register(account_entity);

    }

    @PostMapping("/login")
    public ResponseEntity<RestBean.RestData<Object>> login(@RequestParam("account") String account, @RequestParam("password") String password){
        if(account == null || password == null) return RestBean.failure("缺少参数");
        return accountService.login(account,password);
    }

    @PostMapping("/checkMe")
    public ResponseEntity<RestBean.RestData<Object>> checkMe(@RequestParam("token") String token) {
        return accountService.checkMe(token);
    }
}

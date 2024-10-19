package com.example.service.auth;

import com.example.Util.JwtUtil;
import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.mapper.auth.AccountMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService {
    @Resource
    AccountMapper accountMapper;
    @Resource
    JwtUtil jwtUtil;
    @Resource
    PasswordEncoder passwordEncoder;

    public ResponseEntity<RestBean.RestData<Object>> register(Account account){
        if(account.getId() != null) return RestBean.failure(500,"账号创建失败,传入数据异常");

        Account exist = getAccountByAccount(account.getAccount());
        if(exist == null){
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            String token = jwtUtil.generateToken(account.getAccount());
            account.setToken(token);
            int insert = accountMapper.insert(account);
            if(insert >0) return RestBean.success(Map.of("token",token,"nickname",account.getNickname()),"账号创建成功");
            else return RestBean.failure(500,"账号创建失败");
        }else return RestBean.failure("账号已存在");
    }

    public ResponseEntity<RestBean.RestData<Object>> login(String account, String password){
        Account exist = getAccountByAccount(account);
        if(exist != null){
            boolean matches = passwordEncoder.matches(password, exist.getPassword());
            if(matches){
                String token = updateToken(exist.getAccount());
                if(token != null) return RestBean.success(Map.of("token",token,"nickname",exist.getNickname()),"登录成功,欢迎回来"+exist.getNickname());
                else return RestBean.failure(500,"token更新失败");
            }else return RestBean.failure("密码错误");
        } else return RestBean.failure("账号不存在");
    }

    public ResponseEntity<RestBean.RestData<Object>> checkMe(String token){
        if(token == null || jwtUtil.isTokenExpired(token)) return RestBean.failure("登录已失效");
        else {
            Account account = getAccountByToken(token);
            return RestBean.success(Map.of("token",account.getToken(),"nickname", account.getNickname()));
        }
    }


    public Account getAccountById(int id){
        return accountMapper.selectById(id);
    }

    public Account getAccountByAccount(String account){
        return accountMapper.selectByAccount(account);
    }

    public Account getAccountByToken(String token){
        return accountMapper.selectByToken(token);
    }

    public String updateToken(String account){
        String token = jwtUtil.generateToken(account);
        boolean updated = accountMapper.updateToken(token, account);
        if(updated) return token;
        else return null;
    }
}

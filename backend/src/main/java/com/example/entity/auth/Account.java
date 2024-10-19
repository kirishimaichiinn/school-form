package com.example.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Account {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String nickname;
    String account;
    String password;
    String token;
    Integer status = 1;
}

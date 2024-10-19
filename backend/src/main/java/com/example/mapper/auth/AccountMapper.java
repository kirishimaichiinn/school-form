package com.example.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.auth.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    @Select("SELECT * FROM account WHERE account = #{account}")
    Account selectByAccount(String account);
    @Select("SELECT * FROM account WHERE token = #{token}")
    Account selectByToken(String token);

    @Update("update account set token = #{token} where account = #{account}")
    boolean updateToken(String token,String account);
}

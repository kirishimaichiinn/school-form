package com.example.config;

import com.example.filter.TokenFilter;
import com.example.service.auth.AccountService;
import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfigure {
    @Resource
    AccountService accountService;
    @Bean
    public FilterRegistrationBean<TokenFilter> TokenFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter(accountService));
        registrationBean.addUrlPatterns("/api/post/*");

        return registrationBean;
    }
}

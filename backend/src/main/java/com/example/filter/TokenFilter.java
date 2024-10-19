package com.example.filter;

import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.service.auth.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class TokenFilter implements Filter {
    AccountService accountService;
    public TokenFilter(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token = httpRequest.getParameter("token");

        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
        if(token == null){
            ResponseEntity<RestBean.RestData<Object>> restEntity = RestBean.failure("缺少参数");
            httpResponse.setStatus(restEntity.getStatusCode().value());
            httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(restEntity.getBody()));
            return;
        }else {
            ResponseEntity<RestBean.RestData<Object>> restCheckMeEntity = accountService.checkMe(token);
            if(restCheckMeEntity.getStatusCode().value() != 200){
                httpResponse.setStatus(restCheckMeEntity.getStatusCode().value());
                httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(restCheckMeEntity.getBody()));
                return;
            }else {
                Account account = accountService.getAccountByToken(token);
                httpRequest.setAttribute("account", account);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

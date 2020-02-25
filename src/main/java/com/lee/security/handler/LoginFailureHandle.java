package com.lee.security.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("loginFailureHandle")
public class LoginFailureHandle extends SimpleUrlAuthenticationFailureHandler {

    public static String RETURN_TYPE = "JSON";

    private static final String _JSON = "JSON1";

    private final static Logger logger2 = LoggerFactory.getLogger(LoginFailureHandle.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

       /* if(_JSON.equals(RETURN_TYPE)){
            Map<String, Object> map = new HashMap<>();
            map.put("code",1002);
            map.put("msg", this.analysisException(exception));
            map.put("data",exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(map));
            //super.setDefaultFailureUrl();
        }else{
            super.onAuthenticationFailure(request, response, exception);
        }*/
        String errorMsg = this.analysisException(exception);

        super.setDefaultFailureUrl("/login-error");
        //super.onAuthenticationFailure(request, response, exception);
        request.getRequestDispatcher("/login-error").forward(request, response);
    }

    private String analysisException(Exception ex){
        logger2.error("analysisException:" + ex.getMessage());
        ex.printStackTrace();
        if(ex instanceof NullPointerException){
            return "用户不存在!";
        } else if(ex instanceof BadCredentialsException){
            return "账号密码错误!";
        } else if(ex instanceof LockedException){
            return "该账号被锁定!";
        } else if(ex instanceof AccountExpiredException){
            return "账户已过期!";
        } else if(ex instanceof DisabledException){
            return "该账户不可用!";
        } else if(ex instanceof InternalAuthenticationServiceException){
            return "账户异常，请联系管理员!";
        } else {
            return "系统异常!";
        }
    }
}

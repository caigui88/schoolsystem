package cn.edu.school.schoolservice.handler;

import cn.edu.school.schoolcommon.result.Result;
import cn.edu.school.schoolcommon.utils.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用来处理认证过程中出现的异常
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> result = Result.error("用户名或密码错误");
        String json = objectMapper.writeValueAsString(result);
        WebUtil.renderString(response, json, 401);
    }
}

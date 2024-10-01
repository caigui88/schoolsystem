package cn.edu.school.schoolservice.filter;

import cn.edu.school.schoolcommon.constant.RedisConstant;
import cn.edu.school.schoolcommon.utils.JwtUtil;
import cn.edu.school.schoolservice.entity.LoginAdmin;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * 处理jwt认证的过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 解析jwt以获取id
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Map<String, Object> claims = JwtUtil.parseToken(token);
        Object adminId = claims.get("adminId");

        // 在redis中拉取用户信息
        String redisKey = RedisConstant.LOGIN_KEY + adminId;
        String json = stringRedisTemplate.opsForValue().get(redisKey);
        if (StrUtil.isBlank(json)) {
            throw new RuntimeException("用户未登录");
        }
        LoginAdmin loginAdmin = objectMapper.readValue(json, LoginAdmin.class);

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginAdmin, null, loginAdmin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}

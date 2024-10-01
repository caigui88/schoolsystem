package cn.edu.school.schoolservice.config;

import cn.edu.school.schoolservice.filter.JwtAuthenticationTokenFilter;
import cn.edu.school.schoolservice.handler.AccessDeniedHandlerImpl;
import cn.edu.school.schoolservice.handler.AuthenticationEntryPointHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置Spring Security的过滤链。
     *
     * @param http 用于构建安全配置的HttpSecurity对象。
     * @return 返回配置好的SecurityFilterChain对象。
     * @throws Exception 如果配置过程中发生错误，则抛出异常。
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        // 禁用CSRF保护
        .csrf(AbstractHttpConfigurer::disable)
        // 设置会话创建策略为无状态
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 配置授权规则   指定login路径.允许匿名访问(未登录可访问已登陆不能访问). 其他路径需要身份认证
        .authorizeHttpRequests(
            auth -> auth.requestMatchers(
                "/admin/login",
                "/doc.html",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/webjars/**"
            ).anonymous().anyRequest().authenticated()
        )
        // 配置过滤器，把jwtAuthenticationTokenFilter添加到UsernamePasswordAuthenticationFilter之前
        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        // 配置异常处理器
        .exceptionHandling(
            exception -> exception
                .authenticationEntryPoint(new AuthenticationEntryPointHandler())
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
        );

        // 构建并返回安全过滤链
        return http.build();
    }

}

package cn.edu.school.schoolservice.service.impl;

import cn.edu.school.schoolcommon.constant.RedisConstant;
import cn.edu.school.schoolcommon.domain.dto.LoginDTO;
import cn.edu.school.schoolcommon.result.Result;
import cn.edu.school.schoolcommon.utils.JwtUtil;
import cn.edu.school.schoolservice.entity.Admin;
import cn.edu.school.schoolservice.entity.LoginAdmin;
import cn.edu.school.schoolservice.mapper.AdminMapper;
import cn.edu.school.schoolservice.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService, UserDetailsService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private @Lazy AuthenticationManager authenticationManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * SpringSecurity认证授权
     * @param username 当前登录的用户
     * @return UserDetails封装
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 根据用户名查询用户信息
        Admin admin = lambdaQuery().eq(Admin::getName, username).one();

        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }

        // 查询对应权限信息
        List<String> permission = adminMapper.selectAuthorityByAdminId(admin.getId());

        return new LoginAdmin(admin, permission);
    }

    @Override
    public Result<String> login(LoginDTO loginDTO) {
        // 封装authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword());

        // 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 认证通过，根据id生成token并把用户信息存入redis中
        LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
        Integer adminId = loginAdmin.getAdmin().getId();

        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", adminId);
        String token = JwtUtil.genToken(claims);

        try {

            String json = objectMapper.writeValueAsString(loginAdmin);
            stringRedisTemplate.opsForValue()
                    .set(RedisConstant.LOGIN_KEY + adminId, json, 12, TimeUnit.HOURS);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Result.success(token);
    }

    @Override
    public Result<String> logout() {
        // 获取SecurityContextHolder中的用户信息
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginAdmin loginAdmin = (LoginAdmin) authentication.getPrincipal();

        // 删除redis中的值
        Integer adminId = loginAdmin.getAdmin().getId();
        stringRedisTemplate.delete(RedisConstant.LOGIN_KEY + adminId);
        return Result.success();
    }
}

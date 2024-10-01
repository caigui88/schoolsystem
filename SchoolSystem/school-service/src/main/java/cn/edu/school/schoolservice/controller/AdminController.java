package cn.edu.school.schoolservice.controller;


import cn.edu.school.schoolcommon.domain.dto.LoginDTO;
import cn.edu.school.schoolcommon.result.Result;
import cn.edu.school.schoolservice.service.IAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@RestController
@RequestMapping("/admin")
@Tag(name = "管理员相关接口")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<String> login(@Validated @RequestBody LoginDTO loginDTO) {
        return adminService.login(loginDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "管理员退出登录")
    @PreAuthorize("hasAnyAuthority('system:base')")
    public Result<String> logout() {
        return adminService.logout();
    }

}

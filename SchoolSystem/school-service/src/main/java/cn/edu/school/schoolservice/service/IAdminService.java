package cn.edu.school.schoolservice.service;

import cn.edu.school.schoolcommon.domain.dto.LoginDTO;
import cn.edu.school.schoolcommon.result.Result;
import cn.edu.school.schoolservice.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 管理员登录
     * @param loginDTO 当前管理员信息
     * @return 登录token
     */
    Result<String> login(LoginDTO loginDTO);

    /**
     * 管理员退出登录
     * @return 响应结果
     */
    Result<String> logout();
}

package cn.edu.school.schoolservice.mapper;

import cn.edu.school.schoolservice.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据管理员id查询管理员权限
     * @param id 管理员id
     * @return 管理员权限列表
     */
    List<String> selectAuthorityByAdminId(Integer id);
}

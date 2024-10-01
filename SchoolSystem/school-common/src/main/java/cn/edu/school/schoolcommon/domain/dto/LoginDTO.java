package cn.edu.school.schoolcommon.domain.dto;

import cn.edu.school.schoolcommon.constant.ValidationConstant;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员登录数据模型封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    /**
     * 用户名
     */
    @Pattern(regexp = ValidationConstant.NAME_REGEX,
             message = ValidationConstant.NAME_REGEX_ERROR)
    private String name;

    /**
     * 密码
     */
    @Pattern(regexp = ValidationConstant.PASSWORD_REGEX,
             message = ValidationConstant.PASSWORD_REGEX_ERROR)
    private String password;

}

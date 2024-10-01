package cn.edu.school.schoolservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * LoginAdmin，实现UserDetails接口
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdmin implements UserDetails, Serializable {

    /**
     * 将登录的Admin进行封装
     */
    private Admin admin;

    /**
     * 基本权限信息
     */
    private List<String> permission;

    /**
     * 权限信息封装
     */
    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 双参数构造函数
     * @param admin 需要封装的Admin
     * @param permission 需要封装的权限信息
     */
    public LoginAdmin(Admin admin, List<String> permission) {
        this.admin = admin;
        this.permission = permission;
    }

    /**
     * 封装基本权限信息
     * @return 封装后的权限信息
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 单例模式
        if (authorities != null) {
            return authorities;
        }
        return permission.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return admin.getName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}

package org.mystic.blog.security2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/31 15:05
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class JwtUserDetails implements UserDetails {
    private final Integer userID;
    private final String userName;
    private final String userPWD;
    private final Collection<? extends GrantedAuthority> authorities;

    JwtUserDetails(Integer userID, String userName, String userPWD, Collection<? extends GrantedAuthority> authorities) {
        this.userID = userID;
        this.userName = userName;
        this.userPWD = userPWD;
        this.authorities = authorities;
    }

    /**
     * 返回分配给角色的权限列表
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPWD;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账户是否未过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 证书是否未过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否激活
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getUserID() {
        return userID;
    }

}

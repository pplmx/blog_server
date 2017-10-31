package org.mystic.blog.security;

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
public class JwtUser implements UserDetails {
    private final Integer userID;
    private final String userName;
    private final String userPWD;
    private final String userEmail;
    private final String userPhone;
    private final String userQQ;
    private final int userSex;
    private final String userLastLoginIP;
    private final Date userLastLoginTime;
    private final Collection<? extends GrantedAuthority> authorities;

    JwtUser(Integer userID, String userName, String userPWD, String userEmail, String userPhone, String userQQ, int userSex, String userLastLoginIP, Date userLastLoginTime, Collection<? extends GrantedAuthority> authorities) {
        this.userID = userID;
        this.userName = userName;
        this.userPWD = userPWD;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userQQ = userQQ;
        this.userSex = userSex;
        this.userLastLoginIP = userLastLoginIP;
        this.userLastLoginTime = userLastLoginTime;
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
        return false;
    }

    /**
     * 账户是否被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 证书是否未过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否激活
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public int getUserSex() {
        return userSex;
    }

    public String getUserLastLoginIP() {
        return userLastLoginIP;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }
}

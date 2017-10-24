package org.mystic.blog.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/19 9:09
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

package org.mystic.blog.security;

import org.mystic.blog.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/31 15:24
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserID(),
                user.getUserName(),
                user.getUserPWD(),
                user.getUserEmail(),
                user.getUserPhone(),
                user.getUserQQ(),
                user.getUserSex(),
                user.getUserLastLoginIP(),
                user.getUserLastLoginTime(),
                mapToGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

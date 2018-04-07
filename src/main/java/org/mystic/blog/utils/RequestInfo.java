package org.mystic.blog.utils;

import org.mystic.blog.security2.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2018/1/20 17:15
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Component
public class RequestInfo {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHead}")
    private String prefix;

    public UserDetails requester(HttpServletRequest request) {
        String header = request.getHeader(this.header);
        if (header != null && header.startsWith(prefix)) {
            final String token = header.substring(prefix.length());
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                return userDetailsService.loadUserByUsername(username);
            }
        }
        return null;
    }

}

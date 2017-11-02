package org.mystic.blog.security2.auth;

import lombok.extern.slf4j.Slf4j;
import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.pojo.User;
import org.mystic.blog.security2.JwtTokenUtil;
import org.mystic.blog.security2.JwtUserDetails;
import org.mystic.blog.utils.WebServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 16:38
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserDAO userDAO;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserDAO userDAO) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDAO = userDAO;
    }

    @Override
    public Map<String,Object> register(Map<String, Object> condition, HttpServletRequest request) {
        Map<String, Object> userName = new HashMap<>(16);
        userName.put("userName", condition.get("userName"));
        if (!userDAO.select(userName).isEmpty()) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = condition.get("userPWD").toString();
        condition.replace("userPWD", encoder.encode(rawPassword));
        // 设置默认初始值
        condition.put("userEmail","");
        condition.put("userPhone","");
        condition.put("userQQ","");
        condition.put("userSex",0);
        condition.put("userLastLoginIP", WebServletUtil.getClientIpAddress(request));
        // 先注册用户
        userDAO.insert(condition);
        // 获取注册用户ID
        Map<String,Object> newUser = userDAO.select(userName).get(0);
        Integer userID = Integer.parseInt(newUser.get("userID").toString());
        Map<String, Object> userRole = new HashMap<String, Object>(16) {
            {
                put("userID", userID);
                // 授予ROLE_USER权限
                put("roleID", 1);
            }
        };
        userDAO.insertRole(userRole);
        return newUser;
    }

    @Override
    public String login(Map<String,Object> condition) {
        String username = condition.get("userName").toString();
        String password = condition.get("userPWD").toString();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUserDetails user = (JwtUserDetails) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}

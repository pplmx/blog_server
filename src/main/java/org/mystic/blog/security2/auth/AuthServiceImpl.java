package org.mystic.blog.security2.auth;

import lombok.extern.slf4j.Slf4j;
import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.pojo.User;
import org.mystic.blog.security2.JwtTokenUtil;
import org.mystic.blog.security2.JwtUserDetails;
import org.mystic.blog.utils.ResultFormatter;
import org.mystic.blog.utils.WebServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    private static String CODE = null;

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
            return ResultFormatter.formatResult(500, "username has already existed", null);
        }
        String code = condition.get("code")==null?null:condition.get("code").toString();
        if (code==null||"".equals(code)){
            return ResultFormatter.formatResult(500, "verification code is wrong", null);
        }
        if (code.equals(CODE)){

        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = condition.get("userPWD").toString();
        condition.replace("userPWD", encoder.encode(rawPassword));
        // 设置默认初始值
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
        return ResultFormatter.formatResult(200, "SUCCESS", newUser);
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

    @Override
    public Map<String, Object> mailAuth(Map<String, Object> condition, String sender, JavaMailSender javaMailSender) {
        Map<String, Object> userEmail = new HashMap<>(16);
        String receiveEmail = condition.get("userEmail").toString();
        userEmail.put("userEmail", receiveEmail);
        if (!userDAO.select(userEmail).isEmpty()) {
            // 如果邮箱存在
            return ResultFormatter.formatResult(500, "email has already existed", null);
        }
        // 发送邮件
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(sender,"caoyu.info","UTF-8"));
            helper.setTo(receiveEmail);
            helper.setSubject("标题：验证码");
            // 生成验证码
            CODE = generateVerificationCode();
            String text = "<p style='color:#F00'>"+CODE+"</p>";
            helper.setText(text, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
        Map<String,Object> result = new HashMap<>(16);
        result.put("code",CODE);
        return ResultFormatter.formatResult(200, "Verification Code generated.", result);
    }

    /**
     * 生成随机验证码
     * @return
     */
    private String generateVerificationCode(){
        StringBuilder sb = new StringBuilder();
        int num = 6;
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

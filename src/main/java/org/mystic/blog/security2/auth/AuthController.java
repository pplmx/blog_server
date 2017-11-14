package org.mystic.blog.security2.auth;

import org.mystic.blog.utils.ResultFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 17:15
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private AuthService authService;

    @PostMapping("${jwt.route.authentication.path}")
    public Map<String,Object> createAuthenticationToken(
            @RequestBody Map<String,Object> condition) throws AuthenticationException {
        final String token = authService.login(condition);
        // Return the token
        Map<String,Object> result = new HashMap<>(16);
        result.put("token",token);
        return ResultFormatter.formatResult(200,"SUCCESS",result);
    }

    @GetMapping("${jwt.route.authentication.refresh}")
    public Map<String,Object> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResultFormatter.formatResult(500,"FAILURE",null);
        } else {
            Map<String,Object> result = new HashMap<>(16);
            result.put("refreshedToken",refreshedToken);
            return ResultFormatter.formatResult(200,"SUCCESS",result);
        }
    }

    @PostMapping("${jwt.route.authentication.register}")
    public Map<String, Object> register(@RequestBody Map<String, Object> condition, HttpServletRequest request) throws AuthenticationException {
        return authService.register(condition,request);
    }

    @PostMapping("${jwt.route.authentication.email}")
    public Map<String,Object> authenticateMail(@RequestBody Map<String,Object> condition){
        return authService.mailAuth(condition,sender,javaMailSender);
    }
}

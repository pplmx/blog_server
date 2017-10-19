package org.mystic.blog.security;

import org.mystic.blog.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/19 8:54
 * @since: JDK1.8.0_144
 * @version: X
 * Description: 自定义身份认证验证组件
 */
@Component
class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    private static CustomAuthenticationProvider provider;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        provider = this;
        // 初始化时,将已经静态化的userService实例化
        provider.userService = this.userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Map<String,Object> condition = new HashMap<>(16);
        condition.put("userName",name);
        condition.put("userPWD",password);

        List<Map<String,Object>> userList = provider.userService.findUser(condition);

        // 认证逻辑
        if (userList != null && userList.size()>0) {

            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));
            // 生成令牌并返回
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
            throw new BadCredentialsException("用户名或密码错误~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

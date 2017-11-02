package org.mystic.blog.security2.auth;

import org.mystic.blog.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 16:36
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public interface AuthService {
    /**
     * 用户注册
     * @param condition
     * @param request
     * @return
     */
    Map<String,Object> register(Map<String,Object> condition, HttpServletRequest request);

    /**
     * 用户登录
     * @param condition
     * @return
     */
    String login(Map<String,Object> condition);

    /**
     * token刷新
     *
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
}

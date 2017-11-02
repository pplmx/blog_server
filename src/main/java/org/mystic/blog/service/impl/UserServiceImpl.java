package org.mystic.blog.service.impl;

import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.service.UserService;
import org.mystic.blog.utils.WebServletUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/12 13:40
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    public List<Map<String, Object>> findUser(Map<String, Object> condition) {
        return userDAO.select(condition);
    }

    @Override
    public int saveUser(Map<String, Object> condition, HttpServletRequest request) {
        Object userID = condition.get("userID");
        String userLastLoginIP = WebServletUtil.getClientIpAddress(request);
        condition.put("userLastLoginIP", userLastLoginIP);
        if (userID != null) {
            return userDAO.update(condition);
        }
        return userDAO.insert(condition);
    }

    @Override
    public int deleteUser(Map<String, Object> condition) {
        // 先删除用户的角色信息,再删除用户信息
        userDAO.deleteRole(condition);
        return userDAO.delete(condition);
    }

    @Override
    public int findUserNum(Map<String, Object> condition) {
        return userDAO.selectTotal(condition);
    }

    @Override
    public int deleteUserRole(Map<String, Object> condition) {
        return userDAO.deleteRole(condition);
    }

    @Override
    public int insertUserRole(Map<String, Object> condition) {
        return userDAO.insertRole(condition);
    }
}

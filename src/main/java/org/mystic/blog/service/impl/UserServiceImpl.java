package org.mystic.blog.service.impl;

import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.service.UserService;
import org.mystic.blog.utils.WebServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author: mystic
 * @date: 2017/10/12 13:40
 * @since: JDK1.8.0_144
 * @version:
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    public List<Map<String, Object>> findUser(Map<String,Object> condition) {
        return userDAO.select(condition);
    }

    @Override
    public int saveUser(Map<String, Object> condition,HttpServletRequest request) {
        Object userID = condition.get("userID");
        String userLastLoginIP = WebServletUtil.getClientIpAddress(request);
        condition.put("userLastLoginIP",userLastLoginIP);
        if (userID != null) {
            return userDAO.update(condition);
        }
        return userDAO.insert(condition);
    }

    @Override
    public int deleteUser(Map<String, Object> condition) {
        return userDAO.delete(condition);
    }
}

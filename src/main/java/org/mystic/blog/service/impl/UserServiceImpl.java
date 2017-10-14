package org.mystic.blog.service.impl;

import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 13:40
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Map<String, Object>> findGoods(Map<String,Object> condition) {
        return userDAO.select(condition);
    }
}

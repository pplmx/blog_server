package org.mystic.blog.service.impl;

import org.mystic.blog.dao.RoleDAO;
import org.mystic.blog.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 9:43
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDAO roleDAO;

    @Override
    public int findRoleNum(Map<String, Object> condition) {
        return roleDAO.selectTotal(condition);
    }

    @Override
    public List<Map<String, Object>> findRole(Map<String, Object> condition) {
        return roleDAO.select(condition);
    }

    @Override
    public int saveRole(Map<String, Object> condition) {
        Object roleID = condition.get("roleID");
        if (roleID != null) {
            return roleDAO.update(condition);
        }
        return roleDAO.insert(condition);
    }
}

package org.mystic.blog.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 9:42
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public interface RoleService {
    /**
     * 查询指定条件的角色总数目
     *
     * @param condition 条件
     * @return 总数目
     */
    int findRoleNum(Map<String, Object> condition);

    /**
     * 查询指定条件的角色信息
     *
     * @param condition 条件
     * @return 角色集合
     */
    List<Map<String, Object>> findRole(Map<String, Object> condition);

    /**
     * 保存角色信息
     *
     * @param condition 条件
     * @return 影响的行数
     */
    int saveRole(Map<String, Object> condition);
}

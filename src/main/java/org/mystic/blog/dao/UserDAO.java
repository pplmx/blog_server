package org.mystic.blog.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/12 11:10
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Repository
public interface UserDAO extends BaseDAO {
    /**
     * 授予用户角色
     *
     * @param condition
     * @return
     */
    int insertRole(Map<String, Object> condition);

    /**
     * 撤销用户角色
     *
     * @param condition
     * @return
     */
    int deleteRole(Map<String, Object> condition);

    /**
     * 查看用户角色
     *
     * @param condition 条件
     * @return 用户角色集合
     */
    List<Map<String, Object>> selectRole(Map<String, Object> condition);
}

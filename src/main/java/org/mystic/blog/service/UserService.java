package org.mystic.blog.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author: mystic
 * @date: 2017/10/12 13:39
 * @since: JDK1.8.0_144
 * @version:
 * Description:
 */
public interface UserService {
    /**
     * 根据条件查询用户
     * @param condition 查询的条件
     * @return 查询结果的集合
     */
    List<Map<String,Object>> findUser(Map<String, Object> condition);
}

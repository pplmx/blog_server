package org.mystic.blog.dao;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author: mystic
 * @date: 2017/10/12 10:56
 * @since: JDK1.8.0_144
 * @version:
 * Description:
 */
public interface BaseDAO {

    /**
     * 数据库添加操作
     * @param condition 添加的参数
     * @return 影响的行数
     */
    int insert(Map<String, Object> condition);

    /**
     * 数据库删除操作
     * @param condition 删除的条件
     * @return 影响的行数
     */
    int delete(Map<String, Object> condition);

    /**
     * 数据库更新操作
     * @param condition 更新的条件
     * @return 影响的行数
     */
    int update(Map<String, Object> condition);

    /**
     * 数据库查询操作
     * @param condition 查询的条件
     * @return 查询结果的集合
     */
    List<Map<String,Object>> select(Map<String, Object> condition);

    /**
     * 查询记录总数
     * @param condition 查询的条件
     * @return 总记录数
     */
    int selectTotal(Map<String,Object> condition);

}

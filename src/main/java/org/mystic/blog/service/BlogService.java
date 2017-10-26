package org.mystic.blog.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 14:45
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public interface BlogService {
    /**
     * 查询符合条件的总记录数
     *
     * @param condition 条件
     * @return 总记录数
     */
    int findBlogNum(Map<String, Object> condition);

    /**
     * 查询符合条件的blog集合
     *
     * @param condition 条件
     * @return Blog集合
     */
    List<Map<String, Object>> findBlog(Map<String, Object> condition);

    /**
     * 保存博客信息
     * @param condition 博客信息
     * @return 影响的行数
     */
    int saveBlog(Map<String, Object> condition);

    /**
     * 删除条件Blog信息
     * @param condition 条件
     * @return 影响的行数
     */
    int deleteBlog(Map<String,Object> condition);
}

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
public interface ArticleService {
    /**
     * 根据条件查询文章总数
     *
     * @param condition 条件
     * @return 文章总数
     */
    int findArticleNum(Map<String, Object> condition);

    /**
     * 根据条件查询文章信息
     *
     * @param condition 条件
     * @return 文章信息集合
     */
    List<Map<String, Object>> findArticle(Map<String, Object> condition);

    /**
     * 保存文章信息
     *
     * @param condition 文章信息
     * @param request   request请求
     * @return 影响的行数
     */
    int saveArticle(Map<String, Object> condition, HttpServletRequest request);

    /**
     * 根据条件删除文章信息
     *
     * @param condition 条件
     * @return 影响的行数
     */
    int deleteArticle(Map<String, Object> condition);
}

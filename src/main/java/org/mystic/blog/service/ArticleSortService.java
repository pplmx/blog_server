package org.mystic.blog.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 14:46
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public interface ArticleSortService {
    /**
     * 根据条件查询总类别数
     *
     * @param condition 查询条件
     * @return 总类别数
     */
    int findArticleSortNum(Map<String, Object> condition);

    /**
     * 根据条件查询类别信息
     *
     * @param condition 查询条件
     * @return 类别信息集合
     */
    List<Map<String, Object>> findArticleSort(Map<String, Object> condition);

    /**
     * 保存分类信息
     *
     * @param condition 分类信息
     * @return 影响的行数
     */
    int saveSort(Map<String, Object> condition);

    /**
     * 根据条件删除分类信息
     *
     * @param condition 条件
     * @return 影响的行数
     */
    int deleteSort(Map<String, Object> condition);
}

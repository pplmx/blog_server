package org.mystic.blog.service.impl;

import org.mystic.blog.dao.ArticleSortDAO;
import org.mystic.blog.service.ArticleSortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 14:49
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class ArticleSortServiceImpl implements ArticleSortService {
    @Resource
    private ArticleSortDAO articleSortDAO;

    @Override
    public int findArticleSortNum(Map<String, Object> condition) {
        return articleSortDAO.selectTotal(condition);
    }

    @Override
    public List<Map<String, Object>> findArticleSort(Map<String, Object> condition) {
        return articleSortDAO.select(condition);
    }

    @Override
    public int saveSort(Map<String, Object> condition) {
        Object sortArticleID = condition.get("sortArticleID");
        if (sortArticleID != null) {
            return articleSortDAO.update(condition);
        }
        return articleSortDAO.insert(condition);
    }

    @Override
    public int deleteSort(Map<String, Object> condition) {
        return articleSortDAO.delete(condition);
    }
}

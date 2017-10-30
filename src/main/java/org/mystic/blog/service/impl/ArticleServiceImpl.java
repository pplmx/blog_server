package org.mystic.blog.service.impl;

import org.apache.http.HttpRequest;
import org.mystic.blog.dao.ArticleDAO;
import org.mystic.blog.service.ArticleService;
import org.mystic.blog.utils.WebServletUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDAO articleDAO;

    @Override
    public int findArticleNum(Map<String, Object> condition) {
        return articleDAO.selectTotal(condition);
    }

    @Override
    public List<Map<String, Object>> findArticle(Map<String, Object> condition) {
        return articleDAO.select(condition);
    }

    @Override
    public int saveArticle(Map<String, Object> condition, HttpServletRequest request) {
        Object articleID = condition.get("articleID");
        if (articleID != null) {
            return articleDAO.update(condition);
        }
        // 记录执行添加操作的IP
        String articleIP = WebServletUtil.getClientIpAddress(request);
        condition.put("articleIP",articleIP);
        condition.put("articlePV",0);
        return articleDAO.insert(condition);
    }

    @Override
    public int deleteArticle(Map<String, Object> condition) {
        return articleDAO.delete(condition);
    }
}

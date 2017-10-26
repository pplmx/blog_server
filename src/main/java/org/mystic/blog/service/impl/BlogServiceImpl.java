package org.mystic.blog.service.impl;

import org.mystic.blog.dao.BlogDAO;
import org.mystic.blog.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 14:48
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDAO blogDAO;

    @Override
    public int findBlogNum(Map<String, Object> condition) {
        return blogDAO.selectTotal(condition);
    }

    @Override
    public List<Map<String, Object>> findBlog(Map<String, Object> condition) {
        return blogDAO.select(condition);
    }

    @Override
    public int saveBlog(Map<String, Object> condition) {
        Object blogID = condition.get("blogID");
        if (blogID != null) {
            return blogDAO.update(condition);
        }
        return blogDAO.insert(condition);
    }

    @Override
    public int deleteBlog(Map<String, Object> condition) {
        return blogDAO.delete(condition);
    }


}

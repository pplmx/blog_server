package org.mystic.blog.service.impl;

import org.mystic.blog.dao.ArticleDAO;
import org.mystic.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

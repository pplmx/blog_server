package org.mystic.blog.service.impl;

import org.mystic.blog.dao.ArticleSortDAO;
import org.mystic.blog.service.ArticleSortService;
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
public class ArticleSortServiceImpl implements ArticleSortService {
    @Resource
    private ArticleSortDAO articleSortDAO;
}

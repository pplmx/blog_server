package org.mystic.blog.controller;

import org.mystic.blog.service.ArticleSortService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 15:09
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
@RequestMapping("/sort")
public class ArticleSortController {
    @Resource
    private ArticleSortService articleSortService;
}

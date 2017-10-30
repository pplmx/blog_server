package org.mystic.blog.controller;

import org.mystic.blog.service.ArticleService;
import org.mystic.blog.utils.ResultFormatter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 15:08
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping
    public Map<String, Object> showArticle(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam Map<String, Object> map) {
        map.putIfAbsent("offset", offset);
        map.putIfAbsent("limit", limit);
        map.replace("offset", offset);
        map.replace("limit", limit);

        Map<String, Object> result = new HashMap<>(16);
        result.put("offset", offset);
        result.put("limit", limit);
        result.put("total", articleService.findArticleNum(map));
        result.put("articles", articleService.findArticle(map));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @GetMapping("/{articleID}")
    public Map<String, Object> showArticleByID(@PathVariable Integer articleID, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("articleID", articleID);
        Map<String, Object> result = new HashMap<>(16);
        Map<String, Object> article = articleService.findArticle(map).get(0);
        // 每查询一次,PV+1
        article.replace("articlePV", Integer.parseInt(article.get("articlePV").toString()) + 1);
        articleService.saveArticle(article, request);
        result.put("article", article);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PostMapping
    public Map<String, Object> addArticle(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = articleService.saveArticle(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PutMapping("/{articleID}")
    public Map<String, Object> modifyArticle(@PathVariable("articleID") Integer articleID, @RequestBody Map<String, Object> condition, HttpServletRequest request) {
        condition.put("articleID", articleID);
        int result = articleService.saveArticle(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @DeleteMapping("/{articleID}")
    public Map<String, Object> removeArticle(@PathVariable("articleID") Integer articleID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{articleID});
        int result = articleService.deleteArticle(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

package org.mystic.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mystic.blog.service.ArticleService;
import org.mystic.blog.utils.ResultFormatter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
@Api("API_Article")
@RestController
@RequestMapping("/api/articles")
@CrossOrigin
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ApiOperation(value = "获取所有文章信息", notes = "获取所有文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "查询起始", defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "查询数量", defaultValue = "5", dataType = "integer", paramType = "query")
    })
    @GetMapping
    public Map<String, Object> showArticle(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, Map<String, Object> map) {
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

    @ApiOperation(value = "获取文章信息", notes = "获取文章信息")
    @ApiImplicitParam(name = "articleID", value = "文章ID", required = true, dataType = "integer", paramType = "path")
    @GetMapping("/{articleID}")
    public Map<String, Object> showArticleByID(@PathVariable Integer articleID, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("articleID", articleID);
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> articleList = articleService.findArticle(map);
        Map<String, Object> article = articleList == null ? null : articleList.get(0);
        if (article != null) {
            // 每查询一次,PV+1
            article.replace("articlePV", Integer.parseInt(article.get("articlePV").toString()) + 1);
            articleService.saveArticle(article, request);
        }
        result.put("article", article);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "添加文章信息", notes = "添加文章信息")
    @ApiImplicitParam(name = "condition", value = "文章信息", required = true, dataType = "Map", paramType = "body")
    @PostMapping
    public Map<String, Object> addArticle(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = articleService.saveArticle(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "修改文章信息", notes = "修改文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleID", value = "文章ID", required = true, dataType = "integer", paramType = "path"),
            @ApiImplicitParam(name = "condition", value = "文章需要修改的信息", required = true, dataType = "Map", paramType = "body")
    })
    @PutMapping("/{articleID}")
    public Map<String, Object> modifyArticle(@PathVariable("articleID") Integer articleID, @RequestBody Map<String, Object> condition, HttpServletRequest request) {
        condition.put("articleID", articleID);
        int result = articleService.saveArticle(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "删除文章信息", notes = "删除文章信息")
    @ApiImplicitParam(name = "articleID", value = "文章ID", required = true, dataType = "integer", paramType = "path")
    @DeleteMapping("/{articleID}")
    public Map<String, Object> removeArticle(@PathVariable("articleID") Integer articleID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{articleID});
        int result = articleService.deleteArticle(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

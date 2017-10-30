package org.mystic.blog.controller;

import org.mystic.blog.service.ArticleSortService;
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
 * @date: 2017/10/24 15:09
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
@RequestMapping("/sorts")
public class ArticleSortController {
    @Resource
    private ArticleSortService articleSortService;

    @GetMapping
    public Map<String, Object> showSort(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam Map<String, Object> map) {
        map.putIfAbsent("offset", offset);
        map.putIfAbsent("limit", limit);
        map.replace("offset", offset);
        map.replace("limit", limit);

        Map<String, Object> result = new HashMap<>(16);
        result.put("offset", offset);
        result.put("limit", limit);
        result.put("total", articleSortService.findArticleSortNum(map));
        result.put("sorts", articleSortService.findArticleSort(map));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @GetMapping("/{sortArticleID}")
    public Map<String, Object> showSortByID(@PathVariable Integer sortArticleID) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("sortArticleID", sortArticleID);
        Map<String, Object> result = new HashMap<>(16);
        result.put("sort", articleSortService.findArticleSort(map).get(0));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PostMapping
    public Map<String, Object> addSort(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = articleSortService.saveSort(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PutMapping("/{sortArticleID}")
    public Map<String, Object> modifySort(@PathVariable("sortArticleID") Integer sortArticleID, @RequestBody Map<String, Object> condition) {
        condition.put("sortArticleID", sortArticleID);
        int result = articleSortService.saveSort(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @DeleteMapping("/{sortArticleID}")
    public Map<String, Object> removeSort(@PathVariable("sortArticleID") Integer sortArticleID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{sortArticleID});
        int result = articleSortService.deleteSort(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

package org.mystic.blog.controller;

import org.mystic.blog.service.BlogService;
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
 * @date: 2017/10/24 15:02
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping
    public Map<String, Object> showBlog(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam Map<String, Object> map) {
        map.putIfAbsent("offset", offset);
        map.putIfAbsent("limit", limit);
        map.replace("offset", offset);
        map.replace("limit", limit);

        Map<String, Object> result = new HashMap<>(16);
        result.put("offset", offset);
        result.put("limit", limit);
        result.put("total", blogService.findBlogNum(map));
        result.put("blogs", blogService.findBlog(map));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @GetMapping("/{blogID}")
    public Map<String, Object> showBlogByID(@PathVariable Integer blogID) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("blogID", blogID);
        Map<String, Object> result = new HashMap<>(16);
        result.put("blog", blogService.findBlog(map).get(0));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PostMapping
    public Map<String, Object> addBlog(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = blogService.saveBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PutMapping("/{blogID}")
    public Map<String, Object> modifyBlog(@PathVariable("blogID") Integer blogID, @RequestBody Map<String, Object> condition) {
        condition.put("blogID", blogID);
        int result = blogService.saveBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @DeleteMapping("/{blogID}")
    public Map<String, Object> removeBlog(@PathVariable("blogID") Integer blogID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{blogID});
        int result = blogService.deleteBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

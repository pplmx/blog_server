package org.mystic.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api("API_Blog")
@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Resource
    private BlogService blogService;

    @ApiOperation(value = "获取所有博客信息", notes = "获取所有博客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "查询起始", defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "查询数量", defaultValue = "5", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "map", value = "请求参数", dataType = "Map", paramType = "query")
    })
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

    @ApiOperation(value = "获取博客信息", notes = "获取博客信息")
    @ApiImplicitParam(name = "blogID", value = "博客ID", required = true, dataType = "integer", paramType = "path")
    @GetMapping("/{blogID}")
    public Map<String, Object> showBlogByID(@PathVariable Integer blogID) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("blogID", blogID);
        Map<String, Object> result = new HashMap<>(16);
        result.put("blog", blogService.findBlog(map).get(0));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "添加博客信息", notes = "添加博客信息")
    @ApiImplicitParam(name = "condition", value = "博客信息", required = true, dataType = "Map", paramType = "body")
    @PostMapping
    public Map<String, Object> addBlog(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = blogService.saveBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogID", value = "博客ID", required = true, dataType = "integer", paramType = "path"),
            @ApiImplicitParam(name = "condition", value = "博客需要修改的信息", required = true, dataType = "Map", paramType = "body")
    })
    @PutMapping("/{blogID}")
    public Map<String, Object> modifyBlog(@PathVariable("blogID") Integer blogID, @RequestBody Map<String, Object> condition) {
        condition.put("blogID", blogID);
        int result = blogService.saveBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "删除博客信息", notes = "删除博客信息")
    @ApiImplicitParam(name = "blogID", value = "博客ID", required = true, dataType = "integer", paramType = "path")
    @DeleteMapping("/{blogID}")
    public Map<String, Object> removeBlog(@PathVariable("blogID") Integer blogID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{blogID});
        int result = blogService.deleteBlog(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

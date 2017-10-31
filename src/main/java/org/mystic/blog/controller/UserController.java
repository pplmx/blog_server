package org.mystic.blog.controller;

import io.swagger.annotations.*;
import org.mystic.blog.service.UserService;
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
 * @date: 2017/10/12 13:42
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Api("API_User")
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "查询起始", defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "查询数量", defaultValue = "5", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "map", value = "请求参数", dataType = "Map", paramType = "query")
    })
    @GetMapping
    public Map<String, Object> showUser(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam Map<String, Object> map) {
        // why it is done,to ensure that 'offset' and 'limit' exist in MapCollection,and they are Integer
        // I just don't wanna translate them.
        map.putIfAbsent("offset", offset);
        map.putIfAbsent("limit", limit);
        map.replace("offset", offset);
        map.replace("limit", limit);

        Map<String, Object> result = new HashMap<>(16);
        result.put("offset", offset);
        result.put("limit", limit);
        result.put("total", userService.findUserNum(map));
        result.put("users", userService.findUser(map));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "integer", paramType = "path")
    @GetMapping("/{userID}")
    public Map<String, Object> showUserByID(@PathVariable("userID") Integer userID) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userID", userID);
        Map<String, Object> result = new HashMap<>(16);
        result.put("user", userService.findUser(map).get(0));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @ApiImplicitParam(name = "condition", value = "用户信息", required = true, dataType = "Map", paramType = "body")
    @PostMapping
    public Map<String, Object> addUser(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        int result = userService.saveUser(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "integer", paramType = "path"),
            @ApiImplicitParam(name = "condition", value = "用户需要修改的信息", required = true, dataType = "Map", paramType = "body")
    })
    @PutMapping("/{userID}")
    public Map<String, Object> modifyUser(@PathVariable("userID") Integer userID, @RequestBody Map<String, Object> condition, HttpServletRequest request) {
        condition.put("userID", userID);
        int result = userService.saveUser(condition, request);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    @ApiImplicitParam(name = "userID", value = "用户ID", required = true, dataType = "integer", paramType = "path")
    @DeleteMapping("/{userID}")
    public Map<String, Object> removeUser(@PathVariable("userID") Integer userID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{userID});
        int result = userService.deleteUser(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

}

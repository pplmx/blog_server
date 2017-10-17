package org.mystic.blog.controller;

import org.apache.ibatis.annotations.Param;
import org.mystic.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Version;
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
 * @date: 2017/10/12 13:42
 * @since: JDK1.8.0_144
 * @version: Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public Map<String, Object> showUser(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit, @RequestParam Map<String, Object> map) {
        // why it is done,to ensure that 'offset' and 'limit' exist in MapCollection,and they are Integer
        // I just don't wanna translate it.
        map.putIfAbsent("offset",offset);
        map.putIfAbsent("limit",limit);
        map.replace("offset",offset);
        map.replace("limit",limit);

        Map<String,Object> returnData = new HashMap<>(16);
        returnData.put("offset",offset);
        returnData.put("limit",limit);
        returnData.put("total",userService.findUserNum(map));
        returnData.put("users",userService.findUser(map));
        return returnData;
    }

    @GetMapping("/{userID}")
    public Map<String, Object> showUserByID(@PathVariable("userID") Integer userID) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userID", userID);
        return userService.findUser(map).get(0);
    }

    @PostMapping
    public int addUser(@RequestBody Map<String, Object> condition, HttpServletRequest request) {
        return userService.saveUser(condition, request);
    }

    @PutMapping("/{userID}")
    public int modifyUser(@PathVariable("userID") Integer userID, @RequestBody Map<String, Object> condition, HttpServletRequest request) {
        condition.put("userID", userID);
        return userService.saveUser(condition, request);
    }

    @DeleteMapping("/{userID}")
    public int removeUser(@PathVariable("userID") Integer userID) {
        Map<String, Object> condition = new HashMap<>(16);
        condition.put("ids", new Integer[]{userID});
        return userService.deleteUser(condition);
    }

}

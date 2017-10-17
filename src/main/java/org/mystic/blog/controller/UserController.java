package org.mystic.blog.controller;

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
 * @author: mystic
 * @date: 2017/10/12 13:42
 * @since: JDK1.8.0_144
 * @version:
 * Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public List<Map<String,Object>> showUser(@RequestParam Map<String,Object> map){
        return userService.findUser(map);
    }

    @GetMapping("/{userID}")
    public Map<String,Object> showUserByID(@PathVariable("userID") Integer userID){
        Map<String,Object> map = new HashMap<>(16);
        map.put("userID",userID);
        return userService.findUser(map).get(0);
    }

    @PostMapping
    public int addUser(@RequestBody Map<String,Object> condition, HttpServletRequest request){
        return userService.saveUser(condition,request);
    }

    @PutMapping("/{userID}")
    public int modifyUser(@PathVariable("userID") Integer userID,@RequestBody Map<String,Object> condition, HttpServletRequest request){
        condition.put("userID",userID);
        return userService.saveUser(condition,request);
    }

    @DeleteMapping("/{userID}")
    public int removeUser(@PathVariable("userID") Integer userID){
        Map<String,Object> condition = new HashMap<>(16);
        condition.put("ids",new Integer[]{userID});
        return userService.deleteUser(condition);
    }

}

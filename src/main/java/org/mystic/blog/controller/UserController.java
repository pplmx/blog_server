package org.mystic.blog.controller;

import org.mystic.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 13:42
 * Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("showUser")
    public List<Map<String,Object>> showUser(@RequestParam Map<String,Object> map){
        List<Map<String,Object>> userList = userService.findGoods(map);
        return userList;
    }

}

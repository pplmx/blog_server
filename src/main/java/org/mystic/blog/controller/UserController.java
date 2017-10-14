package org.mystic.blog.controller;

import org.mystic.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 13:42
 * Description:
 */
@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Autowired
    private UserService userService;

    @GET
    @Path("/get")
    public List<Map<String,Object>> showUser(@RequestParam Map<String,Object> map){
        List<Map<String,Object>> userList = userService.findGoods(map);
        return userList;
    }

}

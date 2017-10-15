package org.mystic.blog.controller;

import jersey.repackaged.com.google.common.collect.Collections2;
import org.mystic.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.HashMap;
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
/*@Path("/user")
@Produces(MediaType.APPLICATION_JSON)*/
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*@GET
    @Path("/get")*/
    @GetMapping("/get")
    public List<Map<String,Object>> showUser(@RequestParam Map<String,Object> map){
    /*public List<Map<String,Object>> showUser(@Context UriInfo ui){
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        MultivaluedMap<String, String> pathParams = ui.getPathParameters();
        queryParams.putAll(pathParams);
        Map<String,Object> map = new HashMap<>(16);
        queryParams.forEach((k,v)->{
            map.put(k,v);
        });*/
        List<Map<String,Object>> userList = userService.findGoods(map);
        return userList;
    }

}

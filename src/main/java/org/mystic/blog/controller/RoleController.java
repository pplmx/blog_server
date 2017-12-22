package org.mystic.blog.controller;

import org.mystic.blog.service.RoleService;
import org.mystic.blog.utils.ResultFormatter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/11/1 9:44
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('SUPER')")
@CrossOrigin
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping
    public Map<String, Object> showRole(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("offset", offset);
        result.put("limit", limit);
        // 两个service方法,都且仅仅使用了offset和limit两个键值对
        result.put("total", roleService.findRoleNum(result));
        result.put("roles", roleService.findRole(result));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @GetMapping("/{roleID}")
    public Map<String, Object> showRoleByID(@PathVariable Integer roleID) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("roleID", roleID);
        List<Map<String, Object>> roleList = roleService.findRole(result);
        result.put("role", roleList == null ? null : roleList.get(0));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PostMapping("/getByName")
    public Map<String, Object> showRoleByName(@RequestBody Map<String, Object> condition) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("role", roleService.findRole(condition));
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PostMapping
    public Map<String, Object> addRole(@RequestBody Map<String, Object> condition) {
        int result = roleService.saveRole(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }

    @PutMapping("/{roleID}")
    public Map<String, Object> modifyRole(@PathVariable("roleID") Integer roleID, @RequestBody Map<String, Object> condition) {
        condition.put("roleID", roleID);
        int result = roleService.saveRole(condition);
        return ResultFormatter.formatResult(200, "SUCCESS", result);
    }
}

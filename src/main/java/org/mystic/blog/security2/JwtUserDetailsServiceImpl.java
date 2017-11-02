package org.mystic.blog.security2;

import com.google.common.collect.Lists;
import org.mystic.blog.dao.UserDAO;
import org.mystic.blog.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/31 15:41
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> userName = new HashMap<String, Object>(1) {
            {
                put("userName", username);
            }
        };
        List<Map<String,Object>> userList = userDAO.select(userName);
        if (userList.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        Map<String,Object> userMap = userList.get(0);
        List<Map<String,Object>> userRoleList = userDAO.selectRole(userMap);
        List<String> authorities = new ArrayList<>();
        userRoleList.forEach(userRole->authorities.add(userRole.get("roleName").toString()));
        User user = new User();
        user.setUserID(Integer.parseInt(userMap.get("userID").toString()));
        user.setUserName(userMap.get("userName").toString());
        user.setUserPWD(userMap.get("userPWD").toString());
        user.setRoles(authorities);
        return JwtUserDetailsFactory.create(user);
    }
}

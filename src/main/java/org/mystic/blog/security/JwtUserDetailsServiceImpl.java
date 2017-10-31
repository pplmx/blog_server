package org.mystic.blog.security;

import org.mystic.blog.dao.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> userMap = userDAO.select(userName).get(0);
        return null;
    }
}

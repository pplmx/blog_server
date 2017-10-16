package org.mystic.blog;

import org.glassfish.jersey.server.ResourceConfig;
import org.mystic.blog.controller.UserController;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * @author: mystic
 * @date: 2017/10/14 17:28
 * @since: JDK1.8.0_144
 * @version:
 * Description:
 */
public class JerseyConfiguration extends ResourceConfig{
    public JerseyConfiguration(){
        // 注册类
        // register(UserController.class);
        // 注册包
        // packages("org.mystic.blog.controller");
    }
}

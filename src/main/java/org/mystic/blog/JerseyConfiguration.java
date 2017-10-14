package org.mystic.blog;

import org.glassfish.jersey.server.ResourceConfig;
import org.mystic.blog.controller.UserController;
import org.springframework.stereotype.Component;

/**
 * Created By IDEA
 *
 * @User cy
 * @Date 2017/10/14
 * @Time 17:28
 * @Description Jersey配置
 */
@Component
public class JerseyConfiguration extends ResourceConfig{
    public JerseyConfiguration(){
        // 注册类
        register(UserController.class);
        // 注册包
        packages("org.mystic.blog.controller");
    }
}

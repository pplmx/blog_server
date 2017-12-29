package org.mystic.blog.utils.api;

import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/29 16:41
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class WebMvcRegistrationsConfig extends WebMvcRegistrationsAdapter {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiRequestMappingHandlerMapping();
    }

}

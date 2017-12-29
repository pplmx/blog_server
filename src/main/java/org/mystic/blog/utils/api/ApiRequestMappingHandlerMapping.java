package org.mystic.blog.utils.api;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/29 16:44
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return createCondition(method.getClass());
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        return createCondition(handlerType);
    }

    private static RequestCondition<ApiVersionCondition> createCondition(Class<?> clazz) {
        RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
        if (classRequestMapping == null) {
            return null;
        }
        StringBuilder mappingUrlBuilder = new StringBuilder();
        if (classRequestMapping.value().length > 0) {
            mappingUrlBuilder.append(classRequestMapping.value()[0]);
        }
        String mappingUrl = mappingUrlBuilder.toString();
        String temp = "${version}";
        if (!mappingUrl.contains(temp)) {
            return null;
        }
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(clazz, ApiVersion.class);
        return apiVersion == null ? new ApiVersionCondition(1) : new ApiVersionCondition(apiVersion.value());
    }
}

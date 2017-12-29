package org.mystic.blog.utils.api;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/12/29 16:45
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    /**
     * 路径中版本的前缀， 这里用 /v[1-9]/的形式
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    public int getApiVersion() {
        return apiVersion;
    }

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getPathInfo());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            if (version < apiVersion) {
                // 如果请求的版本号小于配置版本号，则不满足
                return null;
            }
        }
        return this;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        int i = other.getApiVersion() - apiVersion;
        return Integer.compare(i, 0);
    }

}

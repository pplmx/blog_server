package org.mystic.blog.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/18 14:28
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
public class ResultFormatter {

    private ResultFormatter() {
    }

    /**
     * 为了给前端返回指定格式的json字符串
     *
     * @param status  状态码
     * @param message 文本信息
     * @param result  所需结果
     * @return map集合
     */
    public static Map<String, Object> formatResult(Integer status, String message, Object result) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("status", status);
        map.put("message", message);
        map.put("result", result);
        return map;
    }

}

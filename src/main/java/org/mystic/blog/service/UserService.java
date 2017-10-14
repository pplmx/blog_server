package org.mystic.blog.service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 13:39
 * Description:
 */
public interface UserService {
    List<Map<String,Object>> findGoods(Map<String, Object> condition);
}

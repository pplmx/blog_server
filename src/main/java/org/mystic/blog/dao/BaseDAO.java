package org.mystic.blog.dao;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mystic
 * Date: 2017/10/12
 * Time: 10:56
 * Description:
 */
public interface BaseDAO {

    int insert(Map<String, Object> condition);
    int delete(Map<String, Object> condition);
    int update(Map<String, Object> condition);
    List<Map<String,Object>> select(Map<String, Object> condition);

}

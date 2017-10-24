package org.mystic.blog.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: mystic
 * @date: 2017/10/24 10:30
 * @since: JDK1.8.0_144
 * @version: X
 * Description:
 */
@Data
@ToString
public class Blog {
    private Integer blogID;
    private String blogKey;
    private String blogDescription;
    private String blogName;
    private String blogTitle;
    private Integer userID;
}
